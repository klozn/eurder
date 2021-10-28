package com.switchfully.eurder.api.dto.itemgroups;

public class ItemGroupDto {
    private String itemName;
    private int amount;
    private double totalPrice;

    public String getItemName() {
        return itemName;
    }

    public ItemGroupDto setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public ItemGroupDto setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public ItemGroupDto setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}
