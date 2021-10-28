package com.switchfully.eurder.repositories;

import com.switchfully.eurder.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepo {

    private final Map<String, Order> orders = new HashMap<>();

    public Order save(Order order) {
        return orders.put(order.getId(), order);
    }
}
