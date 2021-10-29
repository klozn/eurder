package com.switchfully.eurder.api.dto.items;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.RestockUrgency;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<ItemDtoWithUrgency> toItemOverview(Map<Item, RestockUrgency> idUrgencyMap, RestockUrgency restockUrgencyToFilter) {
        List<ItemDtoWithUrgency> itemDtoWithUrgencyList = new ArrayList<>();
        for (Item item: idUrgencyMap.keySet()) {
            itemDtoWithUrgencyList.add(
                    new ItemDtoWithUrgency()
                            .setId(item.getId())
                            .setName(item.getName())
                            .setDescription(item.getDescription())
                            .setPrice(item.getPrice())
                            .setStock(item.getStock())
                            .setRestockUrgency(idUrgencyMap.get(item))
                            );
        }
        if (restockUrgencyToFilter == null) {
            return itemDtoWithUrgencyList.stream()
                    .sorted(Comparator.comparingInt(ItemDtoWithUrgency::getStock))
                    .collect(Collectors.toList());
        }
        return itemDtoWithUrgencyList.stream()
                .filter(itemDtoWithUrgency -> itemDtoWithUrgency.getRestockUrgency().equals(restockUrgencyToFilter))
                .sorted(Comparator.comparingInt(ItemDtoWithUrgency::getStock))
                .collect(Collectors.toList());
    }
}
