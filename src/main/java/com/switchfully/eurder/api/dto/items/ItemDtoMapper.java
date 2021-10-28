package com.switchfully.eurder.api.dto.items;

import com.switchfully.eurder.domain.Item;

public class ItemDtoMapper {

    public static Item toEntity(CreateItemDto itemDto) {
        return new Item(itemDto.getName(), itemDto.getDescription(), itemDto.getPrice(), itemDto.getStock());
    }
}
