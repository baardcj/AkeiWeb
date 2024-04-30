package org.example.furniture.aeki.data.entities;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.example.furniture.aeki.model.OrderItemProduct;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIncludeProperties({"item", "discount"})
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CustomerOrder customerOrder;

    @OneToOne(fetch = FetchType.EAGER)
    private Furniture furniture;

    @ManyToOne(fetch = FetchType.EAGER)
    private Food food;

    @Builder.Default
    @Column(name="discount", nullable=false)
    private Integer discount = 0;

    @JsonUnwrapped
    @Transient
    private OrderItemProduct orderItemProduct;

    public void setOrderItemProduct(OrderItemProduct orderItemProduct) {
        orderItemProduct.addThisToOrderItem(this);
    }

    @JsonProperty("item")
    public OrderItemProduct getOrderItemProduct() {
        return getFurniture() != null ? getFurniture() : getFood();
    }


}
