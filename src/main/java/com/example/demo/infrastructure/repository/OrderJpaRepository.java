package com.example.demo.infrastructure.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.model.Order;

public interface OrderJpaRepository extends JpaRepository<Order, UUID> {
}