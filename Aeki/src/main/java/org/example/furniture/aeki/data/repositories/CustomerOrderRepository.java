package org.example.furniture.aeki.data.repositories;

import org.example.furniture.aeki.data.entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

}
