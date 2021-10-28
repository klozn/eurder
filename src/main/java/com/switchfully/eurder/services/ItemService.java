package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.items.CreateItemDto;
import com.switchfully.eurder.repositories.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.switchfully.eurder.api.dto.items.ItemDtoMapper.*;

@Service
public class ItemService {

    private final ItemRepo repo;

    @Autowired
    public ItemService(ItemRepo repo) {
        this.repo = repo;
    }

    public boolean createNewItem(CreateItemDto itemDto) {
        if (itemDto == null) {
            throw new NullPointerException("You can't create an Item from a null object.");
        }
        return repo.save(toEntity(itemDto));
    }
}
