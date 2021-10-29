package com.switchfully.eurder.api.dto.items;

public class UpdateItemDto {
    private String name;
    private String description;
    private double price;
    private int stock;

    public String getName() {
        return name;
    }

    public UpdateItemDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UpdateItemDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public UpdateItemDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getStock() {
        return stock;
    }

    public UpdateItemDto setStock(int stock) {
        this.stock = stock;
        return this;
    }
}
