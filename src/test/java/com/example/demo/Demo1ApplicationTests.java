package com.example.demo;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo1ApplicationTests {



    // @Test
    // void shouldCreateOrderAndPublishEvent() {
    //     OrderRepository repo = mock(OrderRepository.class);
    //     EventPublisher publisher = mock(EventPublisher.class);
    //     OrderService service = new OrderService(repo, publisher);
    //     Order savedOrder = new Order(UUID.randomUUID(), "Laptop", 2);
    //     when(repo.save(any(Order.class))).thenReturn(savedOrder);

    //     Order order = service.createOrder("Laptop", 2);

    //     verify(repo).save(any(Order.class));
    //     verify(publisher).publish(any());
    //     assertEquals("Laptop", order.getProduct());
    // }




}
