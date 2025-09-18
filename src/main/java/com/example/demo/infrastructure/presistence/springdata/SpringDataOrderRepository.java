package com.example.demo.infrastructure.presistence.springdata;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.infrastructure.presistence.entity.OrderEntity;

public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, UUID> {
}
