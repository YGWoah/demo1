package com.example.demo.domain.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.domain.model.Order;
import com.example.demo.domain.port.EventPublisher;
import com.example.demo.domain.port.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final EventPublisher publisher;

    public OrderService(OrderRepository repository, EventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    public Order createOrder(String product, int quantity) {
        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException("product required");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be > 0");
        }
        Order order = new Order(product, quantity);
        Order saved = repository.save(order);
        if (publisher != null) {
            publisher.publish(saved);
        }
        return saved;
    }

    public ArrayList<Order> getAllOrders() {
        return repository.getAllOrders();
    }

    public Optional<Order> getOrder(UUID id) {
        return repository.findById(id);
    }

    public Optional<Order> updateOrder(UUID id, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be > 0");
        }
        Optional<Order> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return Optional.empty();
        }
        Order o = existing.get();
        o.setQuantity(quantity);
        Order saved = repository.save(o);
        if (publisher != null) {
            publisher.publish(saved);
        }
        return Optional.of(saved);
    }

    public boolean deleteOrder(UUID id) {
        Optional<Order> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}