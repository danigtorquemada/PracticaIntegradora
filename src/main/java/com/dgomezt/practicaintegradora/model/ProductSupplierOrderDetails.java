package com.dgomezt.practicaintegradora.model;

import com.dgomezt.practicaintegradora.model.embeddables.ProductSupplierOrderKey;
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
@Table(name = "product_supplier_order_details")
public class ProductSupplierOrderDetails {
    @EmbeddedId
    private ProductSupplierOrderKey productSupplierOrderKey;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_prodSuppOrdDetails_productId"))
    private Product product;

    @ManyToOne
    @MapsId("supplierOrderId")
    @JoinColumn(name = "supplier_order_id", foreignKey = @ForeignKey(name = "FK_prodSuppOrdDetails_supplierOrderId"))
    private SupplierOrder supplierOrder;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;
}