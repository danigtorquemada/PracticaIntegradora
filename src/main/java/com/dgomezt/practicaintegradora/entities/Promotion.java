package com.dgomezt.practicaintegradora.entities;

import com.dgomezt.practicaintegradora.entities.embeddables.Auditory;
import com.dgomezt.practicaintegradora.entities.embeddables.Period;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description")
    private String description;

    @Embedded
    private Period period;

    @Column(name = "discount", precision = 19, scale = 2)
    private BigDecimal discount;

    @Embedded
    private Auditory auditory;

    @ManyToMany
    @JoinTable(name = "promotion_products",
            joinColumns = @JoinColumn(name = "promotion_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new LinkedHashSet<>();

    public Auditory getAuditory() {
        if(auditory == null)
            auditory = new Auditory();

        return auditory;
    }
}