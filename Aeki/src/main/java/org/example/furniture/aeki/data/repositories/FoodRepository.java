package org.example.furniture.aeki.data.repositories;

import org.example.furniture.aeki.data.entities.Food;
import org.example.furniture.aeki.model.OrderItemProduct;
import org.example.furniture.aeki.model.enums.FoodFlavour;
import org.example.furniture.aeki.model.enums.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<OrderItemProduct> findByTypeAndFlavour(FoodType type, FoodFlavour foodFlavour);
}
