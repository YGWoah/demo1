package com.example.demo.domain.repository;

import java.util.Optional;

import com.example.demo.domain.model.Order;

public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(String id);
}
