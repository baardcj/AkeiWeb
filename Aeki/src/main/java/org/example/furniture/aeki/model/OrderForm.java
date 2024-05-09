package org.example.furniture.aeki.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.furniture.aeki.model.enums.FoodFlavour;
import org.example.furniture.aeki.model.enums.FoodType;

@Setter
@Getter
@ToString
@Builder // for testing
public class OrderForm {
    private Integer itemNumber;
    private FoodType type;
    private FoodFlavour foodFlavour;

    @JsonIgnore
    public boolean isProductOrder() {
        return getItemNumber() != null;
    }

    @JsonIgnore
    public boolean isFoodOrder() {
        return !isProductOrder() && (getType() != null && getFoodFlavour() != null);
    }

    @JsonIgnore
    public boolean isWellFormed() {
        return isProductOrder() || isFoodOrder();
    }
}
