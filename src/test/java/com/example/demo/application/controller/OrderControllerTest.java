package com.example.demo.application.controller;

import com.example.demo.application.service.OrderService;
import com.example.demo.domain.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    OrderService orderService;

    @Test
    void createOrder_callsService() throws Exception {
        mvc.perform(post("/orders").param("product", "Phone").param("quantity", "2"))
                .andExpect(status().isOk());

        verify(orderService).createOrder("Phone", 2);
    }

    @Test
    void getOrders_returnsList() throws Exception {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("Phone", 1));
        when(orderService.getAllOrders()).thenReturn(orders);

        mvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].product").value("Phone"));
    }

    @Test
    void getOrderById_found() throws Exception {
        UUID id = UUID.randomUUID();
        when(orderService.getOneOrder(id)).thenReturn(Optional.of(new Order(id, "Tablet", 1)));

        mvc.perform(get("/orders/{id}", id.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product").value("Tablet"));
    }

    @Test
    void getOrderById_notFound() throws Exception {
        UUID id = UUID.randomUUID();
        when(orderService.getOneOrder(id)).thenReturn(Optional.empty());

        mvc.perform(get("/orders/{id}", id.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateQuantity_success() throws Exception {
        UUID id = UUID.randomUUID();
        when(orderService.updateOrder(id, 3)).thenReturn(Optional.of(new Order(id, "Keyboard", 3)));

        mvc.perform(put("/orders/{id}", id.toString()).param("quantity", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(3));
    }

    @Test
    void updateQuantity_badRequest() throws Exception {
        UUID id = UUID.randomUUID();
        mvc.perform(put("/orders/{id}", id.toString()).param("quantity", "0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteOrder_successAndNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        when(orderService.deleteOrder(id)).thenReturn(true);
        mvc.perform(delete("/orders/{id}", id.toString()))
                .andExpect(status().isNoContent());

        when(orderService.deleteOrder(id)).thenReturn(false);
        mvc.perform(delete("/orders/{id}", id.toString()))
                .andExpect(status().isNotFound());
    }
}
