package com.example.demo.infrastructure.presistence.adapter;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Product;
import com.example.demo.domain.repository.ProductRepository;
import com.example.demo.infrastructure.presistence.entity.ProductEntity;
import com.example.demo.infrastructure.presistence.springdata.SpringDataProductRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class ProductRepositoryAdapter implements ProductRepository {

    private final SpringDataProductRepository jpaRepository;

    @Override
    public void save(Product product) {
        jpaRepository.save(new ProductEntity(product.getId(), product.getName(), product.getSku(), product.getPrice(), product.getStock(), product.getDescription()));

    }

    @Override
    public Optional<Product> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(entity -> new Product(entity.getId(), entity.getName(), entity.getSku(), entity.getPrice(), entity.getStock(), entity.getDescription()));
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        return new ArrayList(jpaRepository.findAll());
    }

    @Override
    public Optional<Product> getProduct(UUID id) {
        return findById(id);
    }

    @Override
    public Product update(Product product) {
        ProductEntity entity = new ProductEntity(product.getId(), product.getName(), product.getSku(), product.getPrice(), product.getStock(), product.getDescription());
        ProductEntity saved = jpaRepository.save(entity);
        return new Product(saved.getId(), saved.getName(), saved.getSku(), saved.getPrice(), saved.getStock(), saved.getDescription());
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }

}
