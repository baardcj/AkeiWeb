package org.example.furniture.aeki.data.entities.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class CustomerDiscountId implements Serializable {

    Long customer;
    Long discount;
}
