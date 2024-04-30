package org.example.furniture.aeki.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.furniture.aeki.model.Discountable;

@Getter
@AllArgsConstructor
public enum FoodType implements Discountable {
    HOT_DOG(true, 5),
    HOT_DOG_VEGETARIAN(false, 0);

    public final boolean memberDiscount;
    public final int discount;
}
