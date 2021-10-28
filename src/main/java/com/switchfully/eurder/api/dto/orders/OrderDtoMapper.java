package com.switchfully.eurder.api.dto.orders;

import com.switchfully.eurder.domain.ItemGroup;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDtoMapper {
    private final ItemService itemService;

    @Autowired
    public OrderDtoMapper(ItemService itemService) {
        this.itemService = itemService;
    }

    public Order toEntity(CreateOrderDto orderDto, String customerId) {
        List<ItemGroup> itemGroups = orderDto.getItemGroups().stream()
                .map(this::toEntity)
                .collect(Collectors.toList());

        double totalPrice = 0;
        for (ItemGroup itemGroup : itemGroups) {
            String itemId = itemGroup.getItemId();
            totalPrice += itemService.getPrice(itemId) * itemGroup.getAmount();
        }
        return new Order(itemGroups, totalPrice, customerId);
    }

    private ItemGroup toEntity(CreateItemGroupDto itemGroupDto) {
        LocalDate shippingDate = LocalDate.now().plusDays(1);
        if (itemService.getStock(itemGroupDto.getItemId()) < itemGroupDto.getAmount()) {
            shippingDate = LocalDate.now().plusDays(7);
        }
        return new ItemGroup(itemGroupDto.getItemId(), itemGroupDto.getAmount(), shippingDate);
    }
}
