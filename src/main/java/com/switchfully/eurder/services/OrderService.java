package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.orders.CreateOrderDto;
import com.switchfully.eurder.api.dto.orders.OrderDtoMapper;
import com.switchfully.eurder.api.dto.orders.OrderReportDto;
import com.switchfully.eurder.domain.ItemGroup;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.domain.exceptions.UnauthorizedUserException;
import com.switchfully.eurder.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepo repo;
    private final OrderDtoMapper mapper;
    private final CustomerService customerService;
    private final AdminService adminService;
    private final ItemService itemService;

    @Autowired
    public OrderService(OrderRepo repo, OrderDtoMapper mapper, CustomerService customerService, AdminService adminService, ItemService itemService) {
        this.repo = repo;
        this.mapper = mapper;
        this.customerService = customerService;
        this.adminService = adminService;
        this.itemService = itemService;
    }

    public boolean createNewOrder(CreateOrderDto orderDto, String userId) {
        if (orderDto == null) {
            throw new IllegalArgumentException("You can't create an order from a null object.");
        }
        customerService.assertCustomerId(userId);
        Order order = mapper.toEntity(orderDto, userId);
        return repo.addToQueue(order);
    }

    public OrderReportDto getOrdersByCustomerID(String authorizedUserId) {
        customerService.assertCustomerId(authorizedUserId);
        List<Order> orders = repo.getAll().stream()
                .filter(order -> order.getCustomerId().equals(authorizedUserId))
                .collect(Collectors.toList());
        return mapper.toOrderReportDto(orders);
    }

    public boolean reorder(String orderId, String authorizedUserId) {
        Order order = fetchOrderIfExistsElseThrowException(orderId);
        if (!order.getCustomerId().equals(authorizedUserId)) {
            throw new UnauthorizedUserException("You are not the owner of the order with id: " + orderId);
        }
        return createNewOrder(mapper.toCreateOrderDto(order), authorizedUserId);
    }

    public Map<String, List<Order>> getOrdersPerCustomerId() {
        return repo.getAll().stream()
                .collect(Collectors.groupingBy(Order::getCustomerId));
    }


    public void process(String authorizedUserId) {
        adminService.assertAdminId(authorizedUserId);
        List<Order> uncompletedOrders = new ArrayList<>();
        while (repo.peek() != null) {
            Order order = repo.pop();
            List<ItemGroup> unReservedItemGroups = order.getItemGroups().stream()
                    .filter(itemGroup -> !itemGroup.isReserved())
                    .collect(Collectors.toList());
            unReservedItemGroups.forEach(itemGroup ->
                    itemGroup.setReserved(itemService.deductFromStock(itemGroup.getItemId(), itemGroup.getAmount())));
            order.getItemGroups().removeIf(ItemGroup::isReserved);
            if (order.getItemGroups().size() != 0) {
                uncompletedOrders.add(order);
            }
        }
        uncompletedOrders.forEach(repo::addToQueue);
    }

    private Order fetchOrderIfExistsElseThrowException(String orderId) {
        Order order = repo.getById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order with id: " + orderId + " not found.");
        }
        return order;
    }
}
