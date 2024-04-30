package org.example.furniture.aeki.data.entities;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.furniture.aeki.model.Discountable;
import org.example.furniture.aeki.model.enums.FurnitureType;
import org.example.furniture.aeki.model.OrderItemProduct;
import org.example.furniture.aeki.model.enums.FurnitureStatus;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIncludeProperties({"type", "itemNumber", "description", "price"})
public class Furniture implements OrderItemProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private FurnitureType type;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private FurnitureStatus status;

    @Column(name="item_number")
    private Integer itemNumber;

    @Column(name="price")
    private Integer price;

    @Column(name="description")
    private String description;

    @Column(name="weight")
    private Integer weight;

    @Column(name="color")
    private String colour;

    @Override
    public void addThisToOrderItem(OrderItem orderItem) {
        orderItem.setFurniture(this);
    }

    @Override
    public boolean canBeOrdered() {
        return getStatus().isCanBeOrdered();
    }

    @Override
    public void markAsOrdered() {
        setStatus(FurnitureStatus.SOLD);
    }
    
    private void setStatus(FurnitureStatus furnitureStatus) {
        this.status = furnitureStatus;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public Discountable getDiscountable() {
        return type;
    }
}