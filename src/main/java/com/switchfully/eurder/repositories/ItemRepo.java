package com.switchfully.eurder.repositories;

import com.switchfully.eurder.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Set;

@Repository
public class ItemRepo {
    private Set<Item> items;

    public Set<Item> getAll() {
        return Collections.unmodifiableSet(items);
    }

    public boolean save(Item item) {
        return items.add(item);
    }
}
