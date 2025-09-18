package com.example.demo.application.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.domain.model.Product;
import com.example.demo.domain.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(String name, String sku, BigDecimal price, int stock, String description) {
        Product product = new Product(name, sku, price, stock, description);
        productRepository.save(product);
    }

    public ArrayList<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Optional<Product> getOneProduct(UUID id) {
        return productRepository.getProduct(id);
    }

    @Transactional
    public Optional<Product> updateProduct(UUID id, String name, String sku, BigDecimal price, int stock, String description) {
        return productRepository.findById(id).map(existing -> {
            existing.setName(name);
            existing.setSku(sku);
            existing.setPrice(price);
            existing.setStock(stock);
            existing.setDescription(description);

            Product updated = productRepository.update(existing);
            return updated;
        });
    }

    @Transactional
    public boolean deleteProduct(UUID id) {
        productRepository.delete(id);
        return true;
    }

}
