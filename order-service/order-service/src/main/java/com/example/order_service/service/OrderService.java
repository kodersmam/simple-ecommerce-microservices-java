package com.example.order_service.service;


import com.example.order_service.client.InventoryClient;
import com.example.order_service.dto.OrderRequest;
import com.example.order_service.model.Order;
import com.example.order_service.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final InventoryClient inventoryClient;
    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;

    public Order createOrder(OrderRequest request) {

        // 1️⃣ Sprawdzenie dostępności w inventory-service
        boolean available = inventoryClient.isAvailable(
                request.getProductId(),
                request.getQuantity()
        );

        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());

        // Jeśli brak — REJECTED i ZAPISUJEMY DO BAZY
        if (!available) {
            order.setStatus(Order.Status.REJECTED);
            Order saved = orderRepository.save(order);
            orderEventProducer.sendOrderCreated(saved); // event o odrzuceniu też wysyłamy
            return saved;
        }

        // 3️⃣ Jeśli dostępne — CONFIRMED
        order.setStatus(Order.Status.CONFIRMED);

        //  Zapis do DB
        Order saved = orderRepository.save(order);

        // 5Wysyłamy event do Kafka
        orderEventProducer.sendOrderCreated(saved);

        return saved;
    }
}