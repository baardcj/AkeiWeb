package org.example.furniture.aeki.data.repositories;

import org.example.furniture.aeki.data.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}