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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private Client client;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<Order> orders = new LinkedHashSet<>();

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private Cart cart;

}