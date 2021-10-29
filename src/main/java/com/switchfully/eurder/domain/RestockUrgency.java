package com.switchfully.eurder.domain;

public enum RestockUrgency {
    STOCK_LOW, STOCK_MEDIUM, STOCK_HIGH;

    public static RestockUrgency determineRestockUrgency(int stock) {
        if (stock < 5) {
            return STOCK_LOW;
        }
        if (stock < 10) {
            return STOCK_MEDIUM;
        }
        return STOCK_HIGH;
    }
}
