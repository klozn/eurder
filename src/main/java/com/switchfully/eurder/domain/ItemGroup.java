package com.switchfully.eurder.domain;

import java.time.LocalDate;

public class ItemGroup {

    private final String itemId;
    private final int amount;
    private final LocalDate shippingDate;

    public ItemGroup(String itemId, int amount, LocalDate shippingDate) {
        this.itemId = itemId;
        assertValidAmount();
        this.amount = amount;
        assertValidShippingDate();
        this.shippingDate = shippingDate;
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    private void assertValidAmount() {
        if (amount < 1) {
            throw new IllegalArgumentException("Amount can't be lower than 1.");
        }
    }

    private void assertValidShippingDate() {
        if (shippingDate.isBefore(LocalDate.now().plusDays(1)) || shippingDate.isAfter(LocalDate.now().plusDays(7))) {
            throw new IllegalArgumentException("Shipping date must be after today and before a week from now.");
        }
    }
}
