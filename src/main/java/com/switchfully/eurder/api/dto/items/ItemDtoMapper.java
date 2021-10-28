package com.switchfully.eurder.api.dto.items;

import com.switchfully.eurder.domain.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemDtoMapper {

    public Item toEntity(CreateItemDto itemDto) {
        return new Item(itemDto.getName(), itemDto.getDescription(), itemDto.getPrice(), itemDto.getStock());
    }
}
