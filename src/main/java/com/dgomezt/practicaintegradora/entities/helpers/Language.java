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
@Table(name = "language",
        uniqueConstraints = @UniqueConstraint(name = "UK_language_abbreviation", columnNames = {"abbreviation"}))
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "type", column = @Column(name = "language"))
    })
    private Type type;
}