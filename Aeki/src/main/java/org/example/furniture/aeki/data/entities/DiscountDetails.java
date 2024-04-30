package org.example.furniture.aeki.data.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.furniture.aeki.model.Discountable;
import org.example.furniture.aeki.model.enums.FoodType;
import org.example.furniture.aeki.model.enums.FurnitureType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DiscountDetails {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "food_type")
    private FoodType foodType;

    @Enumerated(EnumType.STRING)
    @Column(name = "furniture_type")
    private FurnitureType furnitureType;

    @Column(name = "discount")
    private int discount;

    private Discountable getDiscountable() {
        return foodType != null ? foodType : furnitureType;
    }

    public boolean equalsDiscountable(Discountable discountable) {
        return getDiscountable().equalDiscountable(discountable);
    }
}
