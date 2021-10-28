package com.switchfully.eurder.api.dto.orders;

import com.switchfully.eurder.api.dto.orders.itemgroups.CreateItemGroupDto;

import java.util.List;

public class CreateOrderDto {

    private List<CreateItemGroupDto> itemGroups;

    public List<CreateItemGroupDto> getItemGroups() {
        return itemGroups;
    }
}
