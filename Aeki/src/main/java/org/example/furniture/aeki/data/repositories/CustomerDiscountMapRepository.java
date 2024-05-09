package org.example.furniture.aeki.data.repositories;

import org.example.furniture.aeki.data.entities.CustomerDiscountMap;
import org.hibernate.annotations.Cache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDiscountMapRepository extends JpaRepository<CustomerDiscountMap, Long> {
    List<CustomerDiscountMap> findByCustomerId(Long customerId);
}
