package com.switchfully.eurder.api.dto.orders;

import java.util.List;

public class CreateOrderDto {

    private List<CreateItemGroupDto> itemGroups;

    public List<CreateItemGroupDto> getItemGroups() {
        return itemGroups;
    }
}
