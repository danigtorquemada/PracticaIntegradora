package com.dgomezt.practicaintegradora.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "supplier_id", foreignKey = @ForeignKey(name = "FK_supplier_catalogueId"))
    private Catalogue catalogue;
}