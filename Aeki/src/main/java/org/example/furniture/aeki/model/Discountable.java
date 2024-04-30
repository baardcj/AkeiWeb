package org.example.furniture.aeki.model;

import lombok.Builder;

public interface Discountable {

    default boolean equalDiscountable(Discountable discountable) {
        return this == discountable;
    }
}
