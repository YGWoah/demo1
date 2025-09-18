package com.example.demo.domain.repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.model.Product;

public interface ProductRepository {

    void save(Product product);

    Optional<Product> findById(UUID id);

    ArrayList<Product> getAllProducts();

    Optional<Product> getProduct(UUID id);

    Product update(Product product);

    void delete(UUID id);
}
