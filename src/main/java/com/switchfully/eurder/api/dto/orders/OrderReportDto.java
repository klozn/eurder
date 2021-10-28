package com.switchfully.eurder.api.dto.orders;

import java.util.List;

public class OrderReportDto {
    private List<OrderDto> orders;
    private double totalPrice;

    public List<OrderDto> getOrders() {
        return orders;
    }

    public OrderReportDto setOrders(List<OrderDto> orders) {
        this.orders = orders;
        return this;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public OrderReportDto setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}
