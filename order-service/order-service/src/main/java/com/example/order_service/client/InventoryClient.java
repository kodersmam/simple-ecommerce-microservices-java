package com.example.order_service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class InventoryClient {

    private final RestClient client;

    public InventoryClient(@Value("${inventory.url}") String inventoryUrl) {
        this.client = RestClient.builder()
                .baseUrl(inventoryUrl)
                .build();
    }

    public boolean isAvailable(Long productId, Integer quantity) {
        return client.get()
                .uri("/inventory/check?productId={id}&quantity={qty}", productId, quantity)
                .retrieve()
                .body(Boolean.class);
    }
}