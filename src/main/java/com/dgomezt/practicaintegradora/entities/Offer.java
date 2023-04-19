package com.dgomezt.practicaintegradora.entities;

import com.dgomezt.practicaintegradora.entities.embeddables.Period;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "discount_percent")
    private Double discountPercent;


    @OneToMany(mappedBy = "offer")
    private Set<ProductOfferDetail> productOfferDetails = new LinkedHashSet<>();

    @Embedded
    private Period period;

}