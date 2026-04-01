package com.example.inventory_service.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(Long productId, int quantity) {
        super("Insufficient stock for productId: " + productId + ", requested: " + quantity);
    }
}