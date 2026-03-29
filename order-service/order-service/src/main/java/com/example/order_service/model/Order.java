package com.example.order_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Order(Long productId, Integer quantity, Status status) {
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
    }

    public enum Status {
        CONFIRMED,
        REJECTED
    }
}