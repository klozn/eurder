package com.switchfully.eurder.domain;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {

    private final String id;
    private final List<ItemGroup> itemGroups;
    private final double totalPrice;
    private final String customerId;

    public Order(List<ItemGroup> itemGroups, double totalPrice, String customerId) {
        id = UUID.randomUUID().toString();
        this.itemGroups = itemGroups;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public List<ItemGroup> getItemGroups() {
        return itemGroups;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getCustomerId() {
        return customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
