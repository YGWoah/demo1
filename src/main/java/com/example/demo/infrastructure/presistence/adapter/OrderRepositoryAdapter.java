package com.example.demo.infrastructure.presistence.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Order;
import com.example.demo.domain.repository.OrderRepository;
import com.example.demo.infrastructure.presistence.entity.OrderEntity;
import com.example.demo.infrastructure.presistence.springdata.SpringDataOrderRepository;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final SpringDataOrderRepository jpaRepository;

    public OrderRepositoryAdapter(SpringDataOrderRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Order order) {
        OrderEntity entity = new OrderEntity(order.getProduct(), order.getQuantity());
        OrderEntity saved = jpaRepository.save(entity);
        order.setId(saved.getId());
    }

    @Override
    public Optional<Order> findById(UUID id) {
        try {
            return jpaRepository.findById(id)
                    .map(entity -> new Order(entity.getId(), entity.getProduct(), entity.getQuantity()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public ArrayList<Order> getAllOrders() {
        List<OrderEntity> entities = jpaRepository.findAll();
        ArrayList<Order> list = new ArrayList<>();
        for (OrderEntity e : entities) {
            list.add(new Order(e.getId(), e.getProduct(), e.getQuantity()));
        }
        return list;
    }

    @Override
    public Order update(Order order) {
        OrderEntity entity = new OrderEntity(order.getId(), order.getProduct(), order.getQuantity());
        OrderEntity saved = jpaRepository.save(entity);
        return new Order(saved.getId(), saved.getProduct(), saved.getQuantity());
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<Order> getOrder(UUID id) {
        return jpaRepository.findById(id)
                .map(e -> new Order(e.getId(), e.getProduct(), e.getQuantity()));
    }

}
