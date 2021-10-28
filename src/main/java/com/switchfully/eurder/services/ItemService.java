package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.items.CreateItemDto;
import com.switchfully.eurder.api.dto.items.ItemDtoMapper;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.repositories.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean createNewItem(CreateItemDto itemDto, String authorizedUserId) {
        if (itemDto == null) {
            throw new NullPointerException("You can't create an Item from a null object.");
        }
        adminService.assertAdminId(authorizedUserId);
        return repo.save(mapper.toEntity(itemDto)) != null;
    }

    public double getPrice(String itemId) {
        Item item = fetchItemIfExistsElseThrowException(itemId);
        return item.getPrice();
    }

    public int getStock(String itemId) {
        Item item = fetchItemIfExistsElseThrowException(itemId);
        return item.getStock();
    }

    private Item fetchItemIfExistsElseThrowException(String itemId) {
        Item item = repo.getById(itemId);
        if (item == null) {
            throw new IllegalArgumentException("There is no item with id: " + itemId);
        }
        return item;
    }
}
