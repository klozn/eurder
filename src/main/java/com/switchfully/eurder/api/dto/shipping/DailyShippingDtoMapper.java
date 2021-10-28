package com.switchfully.eurder.api.dto.shipping;

import com.switchfully.eurder.api.dto.itemgroups.ItemGroupDtoMapper;
import com.switchfully.eurder.api.dto.itemgroups.ItemGroupShippingDto;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.domain.users.Address;
import com.switchfully.eurder.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class DailyShippingDtoMapper {

    private final CustomerService customerService;
    private final ItemGroupDtoMapper itemGroupDtoMapper;

    @Autowired
    public DailyShippingDtoMapper(CustomerService customerService, ItemGroupDtoMapper itemGroupDtoMapper) {
        this.customerService = customerService;
        this.itemGroupDtoMapper = itemGroupDtoMapper;
    }

    public DailyShippingDto toDailyShippingDto(LocalDate shippingDay, Map<String, List<Order>> ordersPerCustomerId) {
        List<ItemGroupShippingDto> itemGroupShippingDtos = new ArrayList<>();

        for (String customerId : ordersPerCustomerId.keySet()) {
            Address address = customerService.fetchCustomerIfExistsElseThrowException(customerId).getAddress();
            ordersPerCustomerId.get(customerId).stream()
                    .map(Order::getItemGroups)
                    .flatMap(Collection::stream)
                    .filter(itemGroup -> itemGroup.getShippingDate().equals(shippingDay))
                    .map(itemGroup -> itemGroupDtoMapper.toItemGroupShippingDto(itemGroup, address))
                    .forEach(itemGroupShippingDtos::add);
        }
        return new DailyShippingDto()
                .setShippingDay(shippingDay)
                .setItemGroups(itemGroupShippingDtos);
    }
}
