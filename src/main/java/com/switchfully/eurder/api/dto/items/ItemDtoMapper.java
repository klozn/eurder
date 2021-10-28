package com.switchfully.eurder.api.dto.items;

import com.switchfully.eurder.api.dto.shipping.DailyShippingDto;
import com.switchfully.eurder.api.dto.itemgroups.ItemGroupDtoMapper;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.ItemGroup;
import com.switchfully.eurder.domain.users.Address;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class ItemDtoMapper {

    private ItemGroupDtoMapper itemGroupDtoMapper;

    public Item toEntity(ItemDto itemDto) {
        return new Item(itemDto.getName(), itemDto.getDescription(), itemDto.getPrice(), itemDto.getStock());
    }

    public DailyShippingDto toItemsShippingByDayDto(LocalDate shippingDay, Map<Address, List<ItemGroup>> itemGroupsByAddress) {
        return null;
    }
}
