package com.dgomezt.practicaintegradora.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "catalogue")
public class Catalogue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(mappedBy = "catalogue", orphanRemoval = true)
    private Supplier supplier;

    @OneToMany(mappedBy = "catalogue")
    private Set<ProductCatalogueDetails> productCatalogueDetailses = new LinkedHashSet<>();
}