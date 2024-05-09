package org.example.furniture.aeki.data.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "customer_order")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrder {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Setter
    @Getter
    @Builder.Default
    @Column(name = "price")
    private Integer sum = 0;

    @JsonProperty("items")
    @OneToMany(mappedBy = "customerOrder", fetch = FetchType.EAGER)
    private final Set<OrderItem> orderItems = new HashSet<>(5);

    public void addOrderItem(OrderItem orderItem) {
        addToSum(orderItem.getOrderItemProduct().getPrice() - orderItem.getDiscount());
        this.orderItems.add(orderItem);
    }

    private void addToSum(int price) {
        this.setSum(getSum() + price);
    }
}
