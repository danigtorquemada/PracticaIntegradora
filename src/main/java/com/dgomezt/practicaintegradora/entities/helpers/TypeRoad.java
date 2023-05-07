package com.dgomezt.practicaintegradora.entities.helpers;

import com.dgomezt.practicaintegradora.entities.embeddables.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "type_road",
        uniqueConstraints = @UniqueConstraint(name = "UK_roadType_abbreviation", columnNames = {"abbreviation"}))
public class TypeRoad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "type", column = @Column(name = "type_road"))
    })
    private Type type;
}