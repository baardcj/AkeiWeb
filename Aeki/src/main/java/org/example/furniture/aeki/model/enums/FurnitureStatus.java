package org.example.furniture.aeki.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FurnitureStatus {
    SOLD(false),
    NOT_SOLD(true);

    public final boolean canBeOrdered;
}
