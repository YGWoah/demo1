package com.example.demo.application.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.service.ProductService;
import com.example.demo.domain.model.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void createProduct(@RequestParam String name,
                              @RequestParam String sku,
                              @RequestParam BigDecimal price,
                              @RequestParam int stock,
                              @RequestParam(required = false) String description) {
        log.info("Create product");
        productService.createProduct(name, sku, price, stock, description);
        log.info("Product created");
    }

    @GetMapping
    public ArrayList<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        return productService.getOneProduct(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID id,
                                                 @RequestParam String name,
                                                 @RequestParam String sku,
                                                 @RequestParam BigDecimal price,
                                                 @RequestParam int stock,
                                                 @RequestParam(required = false) String description) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0 || stock < 0) {
            return ResponseEntity.badRequest().build();
        }
        return productService.updateProduct(id, name, sku, price, stock, description)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        boolean deleted = productService.deleteProduct(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
