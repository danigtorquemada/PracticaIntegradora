package com.dgomezt.practicaintegradora.entities.helpers;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@ToString
@Table(name = "client_type")
public class ClientType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "client_type", unique = true)
    private String clientType;

    @Column(name = "discount_percent")
    private BigDecimal discountPercent;

    @Column(name = "min_spend")
    private BigDecimal minSpend;
}

