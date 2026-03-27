package com.example.order_service.dto;


public class OrderRequest {
    private Long productId;
    private Integer quantity;

    public Long getProductId() { return productId; }
    public Integer getQuantity() { return quantity; }
}