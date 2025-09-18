package com.example.demo.infrastructure.presistence.entity;




import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(    
        generator = "UUID"
    )
    private UUID id;
    private String name;
    private String sku;
    private BigDecimal price;
    private int stock;
    private String description;

    
}