package org.example.furniture.aeki.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.furniture.aeki.model.Discountable;

@Getter
@AllArgsConstructor
public enum FoodType implements Discountable {
    HOT_DOG,
    HOT_DOG_VEGETARIAN;
}
