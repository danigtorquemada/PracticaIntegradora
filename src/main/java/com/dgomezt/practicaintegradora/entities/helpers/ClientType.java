package com.dgomezt.practicaintegradora.entities.helpers;

import com.dgomezt.practicaintegradora.entities.embeddables.Type;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@ToString
@Table(name = "client_type",
        uniqueConstraints = @UniqueConstraint(name = "UK_clientType_abbreviation", columnNames = {"abbreviation"}))
public class ClientType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "type", column = @Column(name = "client_type"))
    })
    private Type type;

    @Column(name = "discount_percent")
    private BigDecimal discountPercent;

    @Column(name = "min_spend")
    private BigDecimal minSpend;
}

