package com.example.demo;

import com.example.demo.domain.service.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

}
