package com.switchfully.eurder.api.dto.items;

public class ItemDto {
    private String name;
    private String description;
    private double price;
    private int stock;

    public String getName() {
        return name;
    }

    public ItemDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ItemDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getStock() {
        return stock;
    }

    public ItemDto setStock(int stock) {
        this.stock = stock;
        return this;
    }
}
