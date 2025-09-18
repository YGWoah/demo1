package com.example.demo.application.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.domain.model.Order;
import com.example.demo.domain.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(String product, int amount) {
        Order order = new Order(product, amount);
        orderRepository.save(order);
    }

    public ArrayList<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public Optional<Order> getOneOrder(UUID id) {
        return orderRepository.getOrder(id);
    }

    @Transactional
    public Optional<Order> updateOrder(UUID id, int quantity) {
        return orderRepository.findById(id).map(existing -> {
            existing.setQuantity(quantity);

            Order updated = orderRepository.update(existing);
            return updated;
        });
    }

    @Transactional
    public boolean deleteOrder(UUID id) {
        orderRepository.delete(id);
        return true;
    }

}
