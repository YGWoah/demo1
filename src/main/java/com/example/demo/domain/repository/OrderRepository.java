package com.example.demo.domain.repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.model.Order;

public interface OrderRepository {

    void save(Order order);

    Optional<Order> findById(UUID id);

    ArrayList<Order> getAllOrders();

    Optional<Order> getOrder(UUID id);

    Order update(Order order);

    void delete(UUID id);
}
