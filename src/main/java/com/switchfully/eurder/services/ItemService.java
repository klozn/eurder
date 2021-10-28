package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.items.CreateItemDto;
import com.switchfully.eurder.domain.exceptions.UnauthorizedUserException;
import com.switchfully.eurder.repositories.AdminRepo;
import com.switchfully.eurder.repositories.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.switchfully.eurder.api.dto.items.ItemDtoMapper.*;

@Service
public class ItemService {

    private final ItemRepo repo;
    private final AdminService adminService;


    @Autowired
    public ItemService(ItemRepo repo, AdminService adminService) {
        this.repo = repo;
        this.adminService = adminService;
    }

    public boolean createNewItem(CreateItemDto itemDto, String authorizedUserId) {
        if (itemDto == null) {
            throw new NullPointerException("You can't create an Item from a null object.");
        }
        adminService.assertAdminId(authorizedUserId);
        return repo.save(toEntity(itemDto));
    }
}
