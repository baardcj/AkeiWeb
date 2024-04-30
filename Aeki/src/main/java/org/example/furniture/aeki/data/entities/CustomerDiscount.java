package org.example.furniture.aeki.data.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.furniture.aeki.data.entities.keys.CustomerDiscountId;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@IdClass(CustomerDiscountId.class)
public class CustomerDiscount {

    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;
}
