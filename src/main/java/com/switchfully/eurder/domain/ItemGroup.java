package com.switchfully.eurder.domain;

import java.time.LocalDate;

public class ItemGroup {

    private final String itemId;
    private final int amount;
    private final LocalDate shippingDate;
    private boolean reserved;

    public ItemGroup(String itemId, int amount, LocalDate shippingDate, boolean reserved) {
        this.itemId = itemId;
        this.amount = amount;
        assertValidAmount();
        this.shippingDate = shippingDate;
        assertValidShippingDate();
        this.reserved = reserved;
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

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
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
