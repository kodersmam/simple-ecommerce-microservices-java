package com.example.inventory_service.service;


import com.example.inventory_service.repo.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;

    public boolean isAvailable(Long productId, int requestedQuantity) {
        return repository.findByProductId(productId)
                .map(item -> item.getQuantity() >= requestedQuantity)
                .orElse(false);
    }

    public void decreaseStock(Long productId, int quantity) {
        repository.findByProductId(productId).ifPresent(item -> {
            item.setQuantity(item.getQuantity() - quantity);
            repository.save(item);
        });
    }
}