package com.example.demo.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.domain.model.Product;
import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<Product, UUID> {
}