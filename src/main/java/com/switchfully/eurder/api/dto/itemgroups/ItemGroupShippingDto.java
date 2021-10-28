package com.switchfully.eurder.api.dto.itemgroups;

import com.switchfully.eurder.api.dto.customer.AddressDto;

public class ItemGroupShippingDto {
    private String itemName;
    private int amount;
    private AddressDto address;

    public String getItemName() {
        return itemName;
    }

    public ItemGroupShippingDto setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public ItemGroupShippingDto setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public AddressDto getAddress() {
        return address;
    }

    public ItemGroupShippingDto setAddress(AddressDto address) {
        this.address = address;
        return this;
    }
}
