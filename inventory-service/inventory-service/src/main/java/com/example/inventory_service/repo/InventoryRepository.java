package com.example.inventory_service.repo;


import com.example.inventory_service.model.InventoryItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InventoryRepository {

    private final List<InventoryItem> items = List.of(
            new InventoryItem(1L, 10),
            new InventoryItem(2L, 5),
            new InventoryItem(3L, 20)
    );

    public Optional<Integer> getStockForProduct(Long productId) {
        return items.stream()
                .filter(i -> i.getProductId().equals(productId))
                .map(InventoryItem::getQuantity)
                .findFirst();
    }
}