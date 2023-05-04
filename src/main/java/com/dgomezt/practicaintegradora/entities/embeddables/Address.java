package com.dgomezt.practicaintegradora.entities.embeddables;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private enum TypeRoad{ AVENIDA, CALLE }
    private TypeRoad typeRoad;
    private String name;
    private Integer number;
    private Integer portal;
    private Integer floor;
    private Character leter;
}