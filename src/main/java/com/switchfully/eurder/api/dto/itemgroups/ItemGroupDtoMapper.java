package com.switchfully.eurder.api.dto.itemgroups;

import com.switchfully.eurder.api.dto.customer.AddressDtoMapper;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.ItemGroup;
import com.switchfully.eurder.domain.users.Address;
import com.switchfully.eurder.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ItemGroupDtoMapper {

    private final ItemService itemService;
    private final AddressDtoMapper addressDtoMapper;

    @Autowired
    public ItemGroupDtoMapper(ItemService itemService, AddressDtoMapper addressDtoMapper) {
        this.itemService = itemService;
        this.addressDtoMapper = addressDtoMapper;
    }

    public ItemGroup toEntity(CreateItemGroupDto itemGroupDto) {
        LocalDate shippingDate = LocalDate.now().plusDays(1);
        Item item = itemService.getById(itemGroupDto.getItemId());
        if (item.getStock() < itemGroupDto.getAmount()) {
            shippingDate = LocalDate.now().plusDays(7);
        } else {
            item.setStock(item.getStock() - itemGroupDto.getAmount());
        }
        return new ItemGroup(itemGroupDto.getItemId(), itemGroupDto.getAmount(), shippingDate);
    }

    public ItemGroupDto toDto(ItemGroup itemGroup) {
        Item item = itemService.getById(itemGroup.getItemId());
        double totalPrice = item.getPrice() * itemGroup.getAmount();
        return new ItemGroupDto()
                .setItemName(item.getName())
                .setAmount(itemGroup.getAmount())
                .setTotalPrice(totalPrice);
    }

    public CreateItemGroupDto toCreateItemGroupDto(ItemGroup itemGroup) {
        return new CreateItemGroupDto()
                .setItemId(itemGroup.getItemId())
                .setAmount(itemGroup.getAmount());
    }

    public ItemGroupShippingDto toItemGroupShippingDto(ItemGroup itemGroup, Address address) {
        Item item = itemService.getById(itemGroup.getItemId());
        return new ItemGroupShippingDto()
                .setAddress(addressDtoMapper.toDto(address))
                .setItemName(item.getName())
                .setAmount(itemGroup.getAmount());
    }
}
