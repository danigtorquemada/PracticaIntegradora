package com.dgomezt.practicaintegradora.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "category")
public class Category {
    enum CategoryType{GOLD, SILVER, BRONZE, PLATINUM}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_type", unique = true)
    private CategoryType categoryType;

    @Column(name = "discount_percent")
    private Double discountPercent;

    @Column(name = "min_spend")
    private Integer minSpend;

    @Column(name = "max_spend")
    private Integer maxSpend;
}

