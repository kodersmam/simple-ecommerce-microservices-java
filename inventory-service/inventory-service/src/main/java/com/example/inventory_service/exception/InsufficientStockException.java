package com.example.inventory_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(Long productId, int quantity) {
        super("Insufficient stock for productId: " + productId + ", requested: " + quantity);
    }
}