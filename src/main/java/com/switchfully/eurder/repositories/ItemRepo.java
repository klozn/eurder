package com.switchfully.eurder.repositories;

import com.switchfully.eurder.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepo {
    private final Map<String, Item> items = new HashMap<>();

    public Item save(Item item) {
        return items.put(item.getId(), item);
    }

    public Item getById(String itemId) {
        return items.get(itemId);
    }
}
