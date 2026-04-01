package com.example.order_service.service;


import com.example.order_service.client.InventoryClient;
import com.example.order_service.dto.OrderRequest;
import com.example.order_service.exception.InvalidOrderException;
import com.example.order_service.model.Order;
import com.example.order_service.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final InventoryClient inventoryClient;
    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;

    public Order createOrder(OrderRequest request) {
        if (request.getProductId() == null) {
            throw new InvalidOrderException("productId cannot be null");
        }
        if (request.getQuantity() == null || request.getQuantity() < 1) {
            throw new InvalidOrderException("quantity must be at least 1");
        }

        boolean available = inventoryClient.isAvailable(
                request.getProductId(),
                request.getQuantity()
        );

        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setStatus(available ? Order.Status.CONFIRMED : Order.Status.REJECTED);

        Order saved = orderRepository.save(order);
        orderEventProducer.sendOrderCreated(saved);

        return saved;
    }
}