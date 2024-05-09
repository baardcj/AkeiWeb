package org.example.furniture.aeki.data.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.furniture.aeki.data.entities.keys.CustomerDiscountMapId;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@IdClass(CustomerDiscountMapId.class)
public class CustomerDiscountMap {

    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;
}
