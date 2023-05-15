package com.dgomezt.practicaintegradora.entities.helpers;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "credit_cart")
public class CreditCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credit_cart_type_id", foreignKey = @ForeignKey(name = "FK_creditCart_type"))
    private CreditCartType creditCartType;

    @Column(name = "number")
    private Integer number;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "date_expire")
    private LocalDate dateExpire;
}