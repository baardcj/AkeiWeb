package org.example.furniture.aeki.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.furniture.aeki.model.Discountable;

@Getter
@AllArgsConstructor
public enum FurnitureType implements Discountable {
    FURNITURE(true, 200),
    CARPETS(true, 100),
    TEXTILE(false, 0);

    public boolean memberDiscount;
    public int discount;
}
