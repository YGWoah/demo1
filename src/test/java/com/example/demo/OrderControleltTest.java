package com.example.demo;

import com.example.demo.application.controller.OrderController;
import com.example.demo.application.service.OrderService;

import org.junit.jupiter.api.Test;
import com.example.demo.domain.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    OrderService orderService;

    @Test
    void shouldCreateOrderViaRest() throws Exception {
        // OrderService.createOrder is void; verify it's called with expected params
        mvc.perform(post("/orders")
                        .param("product", "Phone")
                        .param("quantity", "1"))
                .andExpect(status().isOk());

    }

    @Test
    void createOrder_invokesService() throws Exception {
        mvc.perform(post("/orders")
                        .param("product", "Tablet")
                        .param("quantity", "2"))
                .andExpect(status().isOk());

    org.mockito.Mockito.verify(orderService).createOrder("Tablet", 2);
    }

    @Test
    void getOrders_returnsList() throws Exception {
    java.util.ArrayList<Order> orders = new java.util.ArrayList<>();
    orders.add(new Order(java.util.UUID.randomUUID(), "Laptop", 1));
    when(orderService.getAllOrders()).thenReturn(orders);

    mvc.perform(get("/orders"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].product").value("Laptop"));
    }

    @Test
    void getOrderById_found() throws Exception {
    java.util.UUID id = java.util.UUID.randomUUID();
    when(orderService.getOneOrder(id)).thenReturn(java.util.Optional.of(new Order(id, "Tablet", 1)));

    mvc.perform(get("/orders/{id}", id.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.product").value("Tablet"));
    }

    @Test
    void getOrderById_notFound() throws Exception {
    java.util.UUID id = java.util.UUID.randomUUID();
    when(orderService.getOneOrder(id)).thenReturn(java.util.Optional.empty());

    mvc.perform(get("/orders/{id}", id.toString()))
        .andExpect(status().isNotFound());
    }

    @Test
    void updateQuantity_success() throws Exception {
    java.util.UUID id = java.util.UUID.randomUUID();
    when(orderService.updateOrder(id, 3)).thenReturn(java.util.Optional.of(new Order(id, "Keyboard", 3)));

    mvc.perform(put("/orders/{id}", id.toString()).param("quantity", "3"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.quantity").value(3));
    }

    @Test
    void updateQuantity_badRequest_and_notFound() throws Exception {
    java.util.UUID id = java.util.UUID.randomUUID();
    // bad request quantity
    mvc.perform(put("/orders/{id}", id.toString()).param("quantity", "0"))
        .andExpect(status().isBadRequest());

    // not found case
    when(orderService.updateOrder(id, 5)).thenReturn(java.util.Optional.empty());
    mvc.perform(put("/orders/{id}", id.toString()).param("quantity", "5"))
        .andExpect(status().isNotFound());
    }

    @Test
    void deleteOrder_success_and_notFound() throws Exception {
    java.util.UUID id = java.util.UUID.randomUUID();
    when(orderService.deleteOrder(id)).thenReturn(true);
    mvc.perform(delete("/orders/{id}", id.toString()))
        .andExpect(status().isNoContent());

    when(orderService.deleteOrder(id)).thenReturn(false);
    mvc.perform(delete("/orders/{id}", id.toString()))
        .andExpect(status().isNotFound());
    }
}