package com.example.demo.infrastructure.presistence.springdata;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.infrastructure.presistence.Entity.OrderEntity;

public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, String> {
}
