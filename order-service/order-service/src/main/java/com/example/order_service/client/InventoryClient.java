package com.example.order_service.client;


import com.example.order_service.config.InventoryProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class InventoryClient {

    private final InventoryProperties properties;

    private RestClient client;

    @PostConstruct
    public void init() {
        this.client = RestClient.builder()
                .baseUrl(properties.getUrl())
                .build();
    }

    public boolean isAvailable(Long productId, Integer quantity) {
        return client.get()
                .uri("/inventory/check?productId={id}&quantity={qty}", productId, quantity)
                .retrieve()
                .body(Boolean.class);
    }
}