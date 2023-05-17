package com.dgomezt.practicaintegradora.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = "productCartDetails")
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Transient
    private BigDecimal price;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "FK_cart_clientId"))
    private Client client;

    @OneToMany(mappedBy = "cart")
    private Set<ProductCartDetail> productCartDetails = new LinkedHashSet<>();
}