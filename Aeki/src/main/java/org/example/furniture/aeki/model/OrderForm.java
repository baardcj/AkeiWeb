package org.example.furniture.aeki.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.furniture.aeki.model.enums.FoodFlavour;
import org.example.furniture.aeki.model.enums.FoodType;

@Setter
@Getter
@ToString
public class OrderForm {
    private Integer itemNumber;
    private FoodType type;
    private FoodFlavour foodFlavour;

    public boolean isProductOrder() {
        return getItemNumber() != null;
    }

    public boolean isFoodOrder() {
        return !isProductOrder() && (getType() != null && getFoodFlavour() != null);
    }

    public boolean isWellFormed() {
        return isProductOrder() || isFoodOrder();
    }
}
