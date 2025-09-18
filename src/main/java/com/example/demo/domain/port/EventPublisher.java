package com.example.demo.domain.port;


public interface EventPublisher {
    void publish(Object event); // later replace with Kafka
}
