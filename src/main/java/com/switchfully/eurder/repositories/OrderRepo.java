package com.switchfully.eurder.repositories;

import com.switchfully.eurder.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@Repository
public class OrderRepo {

    private final Queue<Order> orders = new PriorityQueue<>();

    public boolean addToQueue(Order order) {
        return orders.add(order);
    }

    public List<Order> getAll() {
        return Collections.unmodifiableList(orders.stream().toList());
    }
}
