package com.example.demo.infrastructure.messaging;

import org.springframework.stereotype.Component;

import com.example.demo.domain.port.EventPublisher;

@Component
public class InMemoryEventPublisher implements EventPublisher {
    @Override
    public void publish(Object event) {
        System.out.println("Event published: " + event);
    }
}
