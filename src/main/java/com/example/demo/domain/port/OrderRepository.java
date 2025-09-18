/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.domain.port;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.domain.model.Order;

public interface OrderRepository {
    Order save(Order order);
    ArrayList<Order> getAllOrders();
    Optional<Order> findById(UUID id);
    void deleteById(UUID id);
}