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
    @OneToOne(mappedBy = "cart", orphanRemoval = true)
    private User user;
    @OneToMany(mappedBy = "cart")
    private Set<ProductCartDetail> productCartDetails = new LinkedHashSet<>();
}