package org.example.furniture.aeki.data.repositories;

import org.example.furniture.aeki.data.entities.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FurnitureRepository extends JpaRepository<Furniture, Long> {
    Optional<Furniture> findByItemNumber(Integer orderForm);
}
