package com.switchfully.eurder.api.dto.orders.itemgroups;

public class CreateItemGroupDto {
    private String itemId;
    private int amount;

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public CreateItemGroupDto setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public CreateItemGroupDto setAmount(int amount) {
        this.amount = amount;
        return this;
    }
}
