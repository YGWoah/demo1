package com.example.demo.infrastructure.presistence.adapter;

import com.example.demo.domain.model.Order;
import com.example.demo.domain.repository.OrderRepository;
import com.example.demo.infrastructure.presistence.Entity.OrderEntity;
import com.example.demo.infrastructure.presistence.springdata.SpringDataOrderRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final SpringDataOrderRepository jpaRepository;

    public OrderRepositoryAdapter(SpringDataOrderRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Order order) {
        OrderEntity entity = new OrderEntity(order.getProduct(), order.getQuantity());

        jpaRepository.save(entity);
    }

    @Override
    public Optional<Order> findById(String id) {
        try {
            return jpaRepository.findById(id)
                    .map(entity -> new Order(entity.getId(), entity.getProduct(), entity.getQuantity()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
