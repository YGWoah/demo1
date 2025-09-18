package com.example.demo.domain.model;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Order {

    private UUID id;
    private UUID product_id;

    private String product;

    private int quantity;

    public Order(UUID id, String product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public Order(UUID id, UUID product_id, String product, int quantity) {
        this.id = id;
        this.product_id = product_id;
        this.product = product;
        this.quantity = quantity;
    }

    public Order(String product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
