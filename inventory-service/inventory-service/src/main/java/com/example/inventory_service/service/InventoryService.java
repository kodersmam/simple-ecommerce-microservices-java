package com.example.inventory_service.service;

import com.example.inventory_service.exception.InsufficientStockException;
import com.example.inventory_service.exception.InventoryNotFoundException;
import com.example.inventory_service.model.InventoryItem;
import com.example.inventory_service.repo.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryService {

    private final InventoryRepository repository;

    public boolean isAvailable(Long productId, int requestedQuantity) {
        return repository.findByProductId(productId)
                .map(item -> item.getQuantity() >= requestedQuantity)
                .orElse(false);
    }

    @Transactional
    public void decreaseStock(Long productId, int quantity) {
        InventoryItem item = repository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException(productId));

        if (item.getQuantity() < quantity) {
            throw new InsufficientStockException(productId, quantity);
        }

        item.setQuantity(item.getQuantity() - quantity);
        repository.save(item);
    }
}