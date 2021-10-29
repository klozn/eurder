package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.items.ItemDtoMapper;
import com.switchfully.eurder.api.dto.items.ItemDtoWithUrgency;
import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.RestockUrgency;
import com.switchfully.eurder.repositories.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemOverviewService {

    private final ItemRepo itemRepo;
    private final AdminService adminService;
    private final ItemDtoMapper mapper;

    @Autowired
    public ItemOverviewService(ItemRepo itemRepo, AdminService adminService, ItemDtoMapper mapper) {
        this.itemRepo = itemRepo;
        this.adminService = adminService;
        this.mapper = mapper;
    }

    public List<ItemDtoWithUrgency> getOverview(RestockUrgency restockUrgency, String authorizedUserId) {
        adminService.assertAdminId(authorizedUserId);
        return mapper.toItemOverview(getRestockUrgencyPerItemIds(), restockUrgency);
    }

    private Map<Item, RestockUrgency> getRestockUrgencyPerItemIds() {
        List<Item> items = itemRepo.getAll();
        Map<Item, RestockUrgency> restockUrgencyPerItemIds = new HashMap<>();
        for (Item item : items) {
            restockUrgencyPerItemIds.put(item, RestockUrgency.determineRestockUrgency(item.getStock()));
        }
        return restockUrgencyPerItemIds;
    }
}
