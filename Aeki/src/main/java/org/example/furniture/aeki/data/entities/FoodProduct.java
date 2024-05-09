package org.example.furniture.aeki.data.entities;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.*;
import org.example.furniture.aeki.model.Discountable;
import org.example.furniture.aeki.model.enums.FoodFlavour;
import org.example.furniture.aeki.model.enums.FoodType;
import org.example.furniture.aeki.model.OrderItemProduct;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIncludeProperties({"type", "itemNumber", "description", "price"})
public class FoodProduct implements OrderItemProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private FoodType type;

    @Enumerated(EnumType.STRING)
    @Column(name="flavor")
    private FoodFlavour flavour;

    @Getter
    @Column(name="price")
    private Integer price;

    @Column(name="description")
    private String description;

    @Setter
    @Column(name="stock")
    private Integer stock;

    @Override
    public void addThisToOrderItem(OrderItem orderItem) {
        orderItem.setFood(this);
    }

    @Override
    public boolean canBeOrdered() {
        return getStock() > 0;
    }

    @Override
    public void markAsOrdered() {
        setStock(getStock() - 1);
    }

    @Override
    public Discountable getDiscountable() {
        return type;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
