package com.dgomezt.practicaintegradora.entities;

import com.dgomezt.practicaintegradora.entities.embeddables.ProductOfferKey;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ProductOfferDetail {
    @EmbeddedId
    private ProductOfferKey productOfferKey;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_prodOffDetails_productId"))
    private Product product;
    @ManyToOne
    @MapsId("offerId")
    @JoinColumn(name = "offer_id", foreignKey = @ForeignKey(name = "FK_prodOffDetails_offerId"))
    private Offer offer;
    @Column(name = "quantity")
    private Integer quantity;
}