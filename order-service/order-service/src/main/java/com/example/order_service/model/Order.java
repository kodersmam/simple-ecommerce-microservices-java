package com.example.order_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        CONFIRMED,
        REJECTED
    }

    // konstruktory
    public Order() {}

    public Order(Long productId, Integer quantity, Status status) {
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
    }

    // gettery i settery
    public Long getId() { return id; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}