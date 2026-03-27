package com.example.inventory_service.service;


import com.example.inventory_service.repo.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;

    public boolean isAvailable(Long productId, int requestedQuantity) {
        return repository.getStockForProduct(productId)
                .map(stock -> stock >= requestedQuantity)
                .orElse(false);
    }
}