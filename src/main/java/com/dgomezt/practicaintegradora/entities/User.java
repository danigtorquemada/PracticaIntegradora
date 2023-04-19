package com.dgomezt.practicaintegradora.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "FK_user_clientID"))
    private Client client;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "cart_id", foreignKey = @ForeignKey(name = "FK_user_cartId"))
    private Cart cart;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Order> orders = new LinkedHashSet<>();
}