package org.example.furniture.aeki.data.entities.keys;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class CustomerDiscountMapId implements Serializable {

    Long customer;
    Long discount;
}
