package com.example.demo.infrastructure.repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Order;
import com.example.demo.domain.port.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class JpaOrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository repo;

    public JpaOrderRepositoryAdapter(OrderJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Order save(Order order) {
        return repo.save(order);
    }

    @Override
    public ArrayList<Order> getAllOrders() {
        return new ArrayList<>(repo.findAll());
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(UUID id) {
        repo.deleteById(id);
    }
}