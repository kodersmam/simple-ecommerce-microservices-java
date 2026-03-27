package com.example.product_service.repo;

import com.example.product_service.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final List<Product> products = List.of(
            new Product(1L, "Laptop", 4500.0),
            new Product(2L, "Smartphone", 2500.0),
            new Product(3L, "Headphones", 300.0)
    );

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}