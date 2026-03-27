package com.example.inventory_service.dto;

import lombok.Data;

@Data
public class OrderCreatedEvent {
    private Long orderId;
    private Long productId;
    private int quantity;
    private String status;

}