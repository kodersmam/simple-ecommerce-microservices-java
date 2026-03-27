package com.example.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // potrzebny domyślny konstruktor dla Kafki
@AllArgsConstructor
public class OrderCreatedEvent {
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private String status;
}