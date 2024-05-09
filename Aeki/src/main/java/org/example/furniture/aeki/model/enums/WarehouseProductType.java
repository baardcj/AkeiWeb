package org.example.furniture.aeki.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.furniture.aeki.model.Discountable;

@Getter
@AllArgsConstructor
public enum WarehouseProductType implements Discountable {
    FURNITURE,
    CARPETS,
    TEXTILE;
}
