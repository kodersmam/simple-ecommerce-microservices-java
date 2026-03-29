package com.example.product_service.service;


import com.example.product_service.model.Product;
import com.example.product_service.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    @Cacheable("products")
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Cacheable(value = "product", key = "#id")
    public Product getById(Long id) {
        return repository.findById(id).orElse(null);
    }
}