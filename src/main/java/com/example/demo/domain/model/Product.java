package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private UUID id;
    private String name;
    private String sku;
    private BigDecimal price;
    private int stock;
    private String description;

    public Product(String name, String sku, BigDecimal price, int stock, String description) {
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }
}