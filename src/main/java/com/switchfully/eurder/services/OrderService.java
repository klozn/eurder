package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.orders.CreateOrderDto;
import com.switchfully.eurder.api.dto.orders.OrderDtoMapper;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepo repo;
    private final OrderDtoMapper mapper;
    private final CustomerService customerService;

    @Autowired
    public OrderService(OrderRepo repo, OrderDtoMapper mapper, CustomerService customerService) {
        this.repo = repo;
        this.mapper = mapper;
        this.customerService = customerService;
    }

    public boolean createNewOrder(CreateOrderDto orderDto, String userId) {
        if (orderDto == null) {
            throw new IllegalArgumentException("You can't create an order from a null object.");
        }
        customerService.assertCustomerId(userId);
        Order order = mapper.toEntity(orderDto, userId);
        return repo.save(order) != null;
    }
}
