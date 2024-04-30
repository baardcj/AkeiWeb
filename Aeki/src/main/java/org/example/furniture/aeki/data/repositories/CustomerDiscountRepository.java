package org.example.furniture.aeki.data.repositories;

import org.example.furniture.aeki.data.entities.Customer;
import org.example.furniture.aeki.data.entities.CustomerDiscount;
import org.example.furniture.aeki.data.entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerDiscountRepository extends JpaRepository<CustomerDiscount, Long> {

    List<CustomerDiscount> findByCustomerId(Long customerId);
}
