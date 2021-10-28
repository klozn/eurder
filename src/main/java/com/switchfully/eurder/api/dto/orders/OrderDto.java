package com.switchfully.eurder.api.dto.orders;

import com.switchfully.eurder.api.dto.itemgroups.ItemGroupDto;

import java.util.List;

public class OrderDto {
    private String orderId;
    private List<ItemGroupDto> itemGroups;
    private double totalPrice;

    public String getOrderId() {
        return orderId;
    }

    public OrderDto setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public List<ItemGroupDto> getItemGroups() {
        return itemGroups;
    }

    public OrderDto setItemGroups(List<ItemGroupDto> itemGroups) {
        this.itemGroups = itemGroups;
        return this;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public OrderDto setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}
