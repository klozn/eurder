package com.switchfully.eurder.api.dto.items;

import com.switchfully.eurder.domain.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemDtoMapper {

    public Item toEntity(UpdateItemDto updateItemDto) {
        return new Item(updateItemDto.getName(), updateItemDto.getDescription(), updateItemDto.getPrice(), updateItemDto.getStock());
    }

    public ItemDto toDto(Item item) {
        return new ItemDto()
                .setId(item.getId())
                .setName(item.getName())
                .setDescription(item.getDescription())
                .setPrice(item.getPrice());
    }
}
