package org.example.furniture.aeki.data.entities;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name="member")
    @Builder.Default
    private boolean member = false;

    @JsonValue
    public long getId() {
        return id;
    }

}
