package com.example.demo.application.service;


import org.springframework.stereotype.Service;

import com.example.demo.domain.model.Order;
import com.example.demo.domain.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(String product , int amount) {
        Order order = new Order(product, amount);
        orderRepository.save(order);
    }
}
