package org.example.furniture.aeki.model;

import org.example.furniture.aeki.data.entities.OrderItem;

public interface OrderItemProduct {

    void addThisToOrderItem(OrderItem orderItem);

    boolean canBeOrdered();

    void markAsOrdered();

    int getPrice();

    Discountable getDiscountable();
}
