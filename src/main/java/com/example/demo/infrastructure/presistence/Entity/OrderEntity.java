package com.example.demo.infrastructure.presistence.Entity;


import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String product;
    private int quantity;


    public OrderEntity(String product,int quantity) {
        this.product=product;
        this.quantity=quantity;
    }

}
