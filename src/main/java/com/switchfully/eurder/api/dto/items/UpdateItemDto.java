package com.switchfully.eurder.api.dto.items;

public class UpdateItemDto extends ItemDto {
    private int stock;

    public int getStock() {
        return stock;
    }

    public UpdateItemDto setStock(int stock) {
        this.stock = stock;
        return this;
    }
}
