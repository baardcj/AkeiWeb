package org.example.furniture.aeki.data.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.furniture.aeki.model.Discountable;
import org.example.furniture.aeki.model.enums.DiscountType;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Discount {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private DiscountType type;

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "discount_details_id")
    private Set<DiscountDetails> discountDetails = new HashSet<>(5);

    public int getDiscount(Discountable discountable) {
        return getDiscountDetails().stream()
                .filter(a -> a.equalsDiscountable(discountable))
                .map(DiscountDetails::getDiscount)
                .reduce(0, Integer::sum);
    }

    public void addDiscountDetails(DiscountDetails discountDetails) {
        this.discountDetails.add(discountDetails);
    }
}
