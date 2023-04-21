package com.dgomezt.practicaintegradora.entities.embeddables;

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
public class ProductCartKey implements Serializable {
    @Column(name ="product_id")
    private Long productId;
    @Column(name = "cart_id")
    private Long cartId;
}