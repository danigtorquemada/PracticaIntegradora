package com.dgomezt.practicaintegradora.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
    @JoinTable(name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id", foreignKey = @ForeignKey(name = "FK_CartProduct_cardId")),
            inverseJoinColumns = @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_CartProduct_productId")))
    private Set<Product> products = new LinkedHashSet<>();

    @OneToOne(mappedBy = "cart", orphanRemoval = true)
    private User user;
}