package com.dgomezt.practicaintegradora.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_orders_userId"))
    private User user;

    @OneToMany(mappedBy = "orderId")
    private Set<ProductOrderDetails> productOrderDetailses = new LinkedHashSet<>();
}