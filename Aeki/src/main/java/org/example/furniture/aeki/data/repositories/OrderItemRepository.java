package org.example.furniture.aeki.data.repositories;

import org.example.furniture.aeki.data.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
