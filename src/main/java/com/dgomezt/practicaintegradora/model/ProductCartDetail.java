package com.dgomezt.practicaintegradora.model;

import com.dgomezt.practicaintegradora.model.embeddables.ProductCartKey;
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
@Table(name = "product_cart_detail")
public class ProductCartDetail {
    @EmbeddedId
    private ProductCartKey productCartKey;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_productCartDetail_productId"))
    private Product product;
    @ManyToOne
    @MapsId("cartId")
    @JoinColumn(name = "cart_id", foreignKey = @ForeignKey(name = "FK_productCartDetail_cartId"))
    private Cart cart;
    @Column(name = "quantity")
    private Integer quantity;
}