package com.switchfully.eurder.api.dto.items;

import com.switchfully.eurder.domain.RestockUrgency;

public class ItemDtoWithUrgency  {
    private String id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private RestockUrgency restockUrgency;

    public String getId() {
        return id;
    }

    public ItemDtoWithUrgency setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemDtoWithUrgency setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ItemDtoWithUrgency setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public ItemDtoWithUrgency setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getStock() {
        return stock;
    }

    public ItemDtoWithUrgency setStock(int stock) {
        this.stock = stock;
        return this;
    }

    public RestockUrgency getRestockUrgency() {
        return restockUrgency;
    }

    public ItemDtoWithUrgency setRestockUrgency(RestockUrgency restockUrgency) {
        this.restockUrgency = restockUrgency;
        return this;
    }
}
