package com.dgomezt.practicaintegradora.model.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class ProductCatalogueKey implements Serializable {
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "catalogue_id")
    private Long catalogueId;
}