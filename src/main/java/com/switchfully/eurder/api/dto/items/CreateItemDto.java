package com.switchfully.eurder.api.dto.items;

public class CreateItemDto {
    private String name;
    private String description;
    private double price;
    private int stock;

    public String getName() {
        return name;
    }

    public CreateItemDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateItemDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public CreateItemDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getStock() {
        return stock;
    }

    public CreateItemDto setStock(int stock) {
        this.stock = stock;
        return this;
    }
}
