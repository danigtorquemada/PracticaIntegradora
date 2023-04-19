package com.dgomezt.practicaintegradora.entities;

import com.dgomezt.practicaintegradora.entities.embeddables.ProductOrderKey;
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
@Table(name = "product_order_details")
public class ProductOrderDetails {
    @EmbeddedId
    private ProductOrderKey productOrderKey;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_prodOrdDetails_productId"))
    private Product product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "FK_prodOrdDetails_orderId"))
    private Order orderId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;
}