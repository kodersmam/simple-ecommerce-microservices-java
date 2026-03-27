# Mini eCommerce Microservices System

Prosty, ale kompletny system e-commerce zbudowany w architekturze **mikrousług** przy użyciu **Java + Spring Boot**. Projekt demonstruje zarówno komunikację synchroniczną (REST), jak i asynchroniczną (Kafka), a także podstawowe wzorce produkcyjne.

## Technologie

- **Java 17+** + **Spring Boot 3**
- **Spring Web**, **Spring Data JPA**, **Spring Kafka**
- **Apache Kafka** – komunikacja asynchroniczna (event-driven)
- **Lombok** – redukcja boilerplate
- **Resilience4j** (opcjonalnie – Retry + Circuit Breaker)
- **Rate Limiting** (Bucket4j)
- **Docker** 
-  **PostgreSQL** 


## Architektura i Mikrousługi

Projekt składa się z trzech mikrousług:

| Mikrousługa       | Port (domyślny) | Główne funkcjonalności                          | Kluczowe wzorce                  |
|-------------------|-----------------|--------------------------------------------------|----------------------------------|
| **product-service**   | 8080           | GET /products<br>GET /products/{id}             | Caching (Redis), Swagger        |
| **inventory-service** | 8081           | GET /inventory/check?productId=&quantity=      | Kafka Consumer, aktualizacja stanów magazynowych |
| **order-service**     | 8082           | POST /orders                                    | Rate Limiting, Kafka Producer, REST do Inventory |
