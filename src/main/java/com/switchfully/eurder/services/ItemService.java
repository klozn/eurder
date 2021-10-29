package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.itemgroups.CreateItemGroupDto;
import com.switchfully.eurder.api.dto.items.ItemDto;
import com.switchfully.eurder.api.dto.items.UpdateItemDto;
import com.switchfully.eurder.api.dto.items.ItemDtoMapper;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.repositories.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepo repo;
    private final AdminService adminService;
    private final ItemDtoMapper mapper;

    @Autowired
    public ItemService(ItemRepo repo, AdminService adminService, ItemDtoMapper mapper) {
        this.repo = repo;
        this.adminService = adminService;
        this.mapper = mapper;
    }

    public boolean createNewItem(UpdateItemDto updateItemDto, String authorizedUserId) {
        if (updateItemDto == null) {
            throw new NullPointerException("You can't create an Item from a null object.");
        }
        adminService.assertAdminId(authorizedUserId);
        return repo.save(mapper.toEntity(updateItemDto)) == null;
    }

    public Item getById(String itemId) {
        return fetchItemIfExistsElseThrowException(itemId);
    }

    private Item fetchItemIfExistsElseThrowException(String itemId) {
        Item item = repo.getById(itemId);
        if (item == null) {
            throw new IllegalArgumentException("There is no item with id: " + itemId);
        }
        return item;
    }

    public boolean updateItem(String itemId, UpdateItemDto updateItemDto, String authorizedUserId) {
        adminService.assertAdminId(authorizedUserId);
        if (updateItemDto == null) {
            throw new NullPointerException("You can't update an Item from a null object.");
        }
        Item item = fetchItemIfExistsElseThrowException(itemId);
        item.setName(updateItemDto.getName());
        item.setDescription(updateItemDto.getDescription());
        item.setPrice(updateItemDto.getPrice());
        item.setStock(updateItemDto.getStock());
        return true;
    }

    public List<ItemDto> getItems() {
        return repo.getAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public LocalDate determineShippingDate(CreateItemGroupDto itemGroupDto) {
        LocalDate shippingDate = LocalDate.now().plusDays(1);
        Item item = getById(itemGroupDto.getItemId());
        if (item.getStock() < itemGroupDto.getAmount()) {
            shippingDate = LocalDate.now().plusDays(7);
        }
        return shippingDate;
    }

    public boolean deductFromStock(String itemId, int minusAmount) {
        Item item = getById(itemId);
        if (item.getStock() >= minusAmount) {
            item.setStock(item.getStock() - minusAmount);
            return true;
        }
        return false;
    }

}
