package com.dgomezt.practicaintegradora.entities;

import com.dgomezt.practicaintegradora.entities.embeddables.Auditory;
import com.dgomezt.practicaintegradora.entities.embeddables.Period;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
            joinColumns = @JoinColumn(name = "promotion_id", foreignKey = @ForeignKey(name = "FK_promotionProducts_promotion")),
            inverseJoinColumns = @JoinColumn(name = "products_id",foreignKey = @ForeignKey(name = "FK_promotionProducts_products")))
    private Set<Product> products = new LinkedHashSet<>();
}