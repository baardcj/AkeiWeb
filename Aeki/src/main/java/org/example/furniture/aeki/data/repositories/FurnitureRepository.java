package org.example.furniture.aeki.data.repositories;

import org.example.furniture.aeki.data.entities.WarehouseProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FurnitureRepository extends JpaRepository<WarehouseProduct, Long> {
    Optional<WarehouseProduct> findByItemNumber(Integer orderForm);
}
