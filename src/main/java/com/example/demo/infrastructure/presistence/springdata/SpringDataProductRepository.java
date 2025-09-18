package com.example.demo.infrastructure.presistence.springdata;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.infrastructure.presistence.entity.ProductEntity;

public interface SpringDataProductRepository extends JpaRepository<ProductEntity, UUID> {
}
