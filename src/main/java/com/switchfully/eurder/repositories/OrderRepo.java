package com.switchfully.eurder.repositories;

import com.switchfully.eurder.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

@Repository
public class OrderRepo {

    private final Deque<Order> orders = new ArrayDeque<>();

    public boolean addToQueue(Order order) {
        return orders.add(order);
    }

    public List<Order> getAll() {
        return Collections.unmodifiableList(orders.stream().toList());
    }

    public Order getById(String orderId) {
        return orders.stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .orElse(null);
    }
}
