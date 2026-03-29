package com.example.inventory_service.kafka;

import com.example.inventory_service.dto.OrderCreatedEvent;
import com.example.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryEventConsumer {

    private final InventoryService inventoryService;

    @KafkaListener(topics = "order-created", groupId = "inventory-group")
    public void consume(OrderCreatedEvent event) {
        if ("CONFIRMED".equals(event.getStatus())) {
            inventoryService.decreaseStock(event.getProductId(), event.getQuantity());
        }
    }
}


