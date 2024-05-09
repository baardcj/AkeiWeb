package org.example.furniture.aeki.data.repositories;

import org.example.furniture.aeki.data.entities.FoodProduct;
import org.example.furniture.aeki.model.OrderItemProduct;
import org.example.furniture.aeki.model.enums.FoodFlavour;
import org.example.furniture.aeki.model.enums.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<FoodProduct, Long> {
    Optional<OrderItemProduct> findByTypeAndFlavour(FoodType type, FoodFlavour foodFlavour);
}
