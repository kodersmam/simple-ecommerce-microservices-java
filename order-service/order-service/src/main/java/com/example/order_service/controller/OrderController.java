package com.example.order_service.controller;

import com.example.order_service.dto.OrderRequest;
import com.example.order_service.model.Order;
import com.example.order_service.service.OrderEventProducer;
import com.example.order_service.service.OrderService;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderEventProducer orderEventProducer;

    @PostMapping
    public Order createOrder(@RequestBody OrderRequest request) {
        // Tworzymy zamówienie w bazie
        Order saved = orderService.createOrder(request);

        // Wysyłamy event do Kafka
        orderEventProducer.sendOrderCreated(saved);

        return saved;
    }
}