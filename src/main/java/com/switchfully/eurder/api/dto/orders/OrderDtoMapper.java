package com.switchfully.eurder.api.dto.orders;

import com.switchfully.eurder.api.dto.orders.itemgroups.ItemGroupDto;
import com.switchfully.eurder.api.dto.orders.itemgroups.ItemGroupDtoMapper;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.ItemGroup;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDtoMapper {

    private final ItemService itemService;
    private final ItemGroupDtoMapper itemGroupDtoMapper;

    @Autowired
    public OrderDtoMapper(ItemService itemService, ItemGroupDtoMapper itemGroupDtoMapper) {
        this.itemService = itemService;
        this.itemGroupDtoMapper = itemGroupDtoMapper;
    }

    public Order toEntity(CreateOrderDto orderDto, String customerId) {
        List<ItemGroup> itemGroups = orderDto.getItemGroups().stream()
                .map(itemGroupDtoMapper::toEntity)
                .collect(Collectors.toList());

        double totalPrice = 0;
        for (ItemGroup itemGroup : itemGroups) {
            Item item = itemService.getById(itemGroup.getItemId());
            totalPrice += item.getPrice() * itemGroup.getAmount();
        }
        return new Order(itemGroups, totalPrice, customerId);
    }

    private OrderDto toDto(Order order) {
        List<ItemGroupDto> itemGroupDtos = order.getItemGroups().stream()
                .map(itemGroupDtoMapper::toDto)
                .collect(Collectors.toList());
        return new OrderDto()
                .setOrderId(order.getId())
                .setItemGroups(itemGroupDtos)
                .setTotalPrice(order.getTotalPrice());
    }

    public OrderReportDto toOrderReportDto(List<Order> orders) {
        List<OrderDto> orderDtos = orders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        double totalPrice = orderDtos.stream()
                .map(OrderDto::getTotalPrice)
                .reduce(Double::sum)
                .orElse(0.);
        return new OrderReportDto()
                .setOrders(orderDtos)
                .setTotalPrice(totalPrice);
    }
}
