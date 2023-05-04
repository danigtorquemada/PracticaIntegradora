package com.dgomezt.practicaintegradora.entities;

import com.dgomezt.practicaintegradora.entities.embeddables.ProductCatalogueKey;
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
@Table(name = "product_catalogue_details")
public class ProductCatalogueDetails {
    @EmbeddedId
    private ProductCatalogueKey productCatalogueKey;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_prodCatDetails_productId"))
    private Product product;
    @ManyToOne
    @MapsId("catalogueId")
    @JoinColumn(name = "catalogue_id", foreignKey = @ForeignKey(name = "FK_prodCatDetails_catalogueId"))
    private Catalogue catalogue;
    @Column(name = "price")
    private Double price;
}