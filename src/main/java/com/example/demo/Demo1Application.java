package com.example.demo;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.application.service.OrderService;
import com.example.demo.application.service.ProductService;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

    @Bean
    public CommandLineRunner createDemoOrdersRunner(OrderService orderService) {
        // This lambda implements the run method of CommandLineRunner
        return args -> {
            System.out.println("Creating demo orders..."); // Optional: log for visibility
            orderService.createOrder("ha", 12);
            System.out.println("Demo orders created!"); // Optional
        };
    }


    @Bean
    public CommandLineRunner createDemoProducts(ProductService productService){
        return args->{
            productService.createProduct("name", "sku", new BigDecimal(200), 100, "description");
        };
    }

}
