package com.example.inventory_service.controller;


import com.example.inventory_service.service.InventoryService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    @GetMapping("/check")
    public boolean checkAvailability(
            @RequestParam Long productId,
            @RequestParam @Min(1) Integer quantity
    ) {
        return service.isAvailable(productId, quantity);
    }
}