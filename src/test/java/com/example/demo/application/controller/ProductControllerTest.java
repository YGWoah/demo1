package com.example.demo.application.controller;

import com.example.demo.application.service.ProductService;
import com.example.demo.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ProductService productService;
    
    @MockBean
    com.example.demo.application.service.OrderService orderService; // satisfy Demo1Application runner dependency

    @Test
    void createProduct_callsService() throws Exception {
        mvc.perform(post("/products")
                        .param("name", "Phone")
                        .param("sku", "P-123")
                        .param("price", "199.99")
                        .param("stock", "5")
                        .param("description", "Nice phone"))
                .andExpect(status().isOk());

        verify(productService).createProduct("Phone", "P-123", new BigDecimal("199.99"), 5, "Nice phone");
    }

    @Test
    void getProducts_returnsList() throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Phone", "P-123", new BigDecimal("199.99"), 5, "Nice phone"));
        when(productService.getAllProducts()).thenReturn(products);

        mvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Phone"));
    }

    @Test
    void getProductById_found() throws Exception {
        UUID id = UUID.randomUUID();
        when(productService.getOneProduct(id)).thenReturn(Optional.of(new Product(id, "Tablet", "T-1", new BigDecimal("299.99"), 3, "Tablet")));

        mvc.perform(get("/products/{id}", id.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Tablet"));
    }

    @Test
    void getProductById_notFound() throws Exception {
        UUID id = UUID.randomUUID();
        when(productService.getOneProduct(id)).thenReturn(Optional.empty());

        mvc.perform(get("/products/{id}", id.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateProduct_success() throws Exception {
        UUID id = UUID.randomUUID();
        when(productService.updateProduct(id, "Keyboard", "K-1", new BigDecimal("49.99"), 10, "Mechanical")).thenReturn(Optional.of(new Product(id, "Keyboard", "K-1", new BigDecimal("49.99"), 10, "Mechanical")));

        mvc.perform(put("/products/{id}", id.toString())
                        .param("name", "Keyboard")
                        .param("sku", "K-1")
                        .param("price", "49.99")
                        .param("stock", "10")
                        .param("description", "Mechanical"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Keyboard"))
                .andExpect(jsonPath("$.stock").value(10));
    }

    @Test
    void updateProduct_badRequest() throws Exception {
        UUID id = UUID.randomUUID();
        mvc.perform(put("/products/{id}", id.toString())
                        .param("name", "Bad")
                        .param("sku", "B-1")
                        .param("price", "-1")
                        .param("stock", "-5")
                        .param("description", "bad"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteProduct_successAndNotFound() throws Exception {
        UUID id = UUID.randomUUID();
        when(productService.deleteProduct(id)).thenReturn(true);
        mvc.perform(delete("/products/{id}", id.toString()))
                .andExpect(status().isNoContent());

        when(productService.deleteProduct(id)).thenReturn(false);
        mvc.perform(delete("/products/{id}", id.toString()))
                .andExpect(status().isNotFound());
    }
}
