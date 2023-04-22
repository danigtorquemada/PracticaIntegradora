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
public class ProductSupplierOrderKey implements Serializable {
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "supplier_order_id")
    private Long supplierOrderId;
}