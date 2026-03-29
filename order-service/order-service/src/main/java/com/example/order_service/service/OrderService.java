package com.example.order_service.service;


import com.example.order_service.client.InventoryClient;
import com.example.order_service.dto.OrderRequest;
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

        boolean available = inventoryClient.isAvailable(
                request.getProductId(),
                request.getQuantity()
        );

        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());

        if (!available) {
            order.setStatus(Order.Status.REJECTED);
            Order saved = orderRepository.save(order);
            orderEventProducer.sendOrderCreated(saved);
            return saved;
        }

        order.setStatus(Order.Status.CONFIRMED);
        Order saved = orderRepository.save(order);
        orderEventProducer.sendOrderCreated(saved);

        return saved;
    }
}