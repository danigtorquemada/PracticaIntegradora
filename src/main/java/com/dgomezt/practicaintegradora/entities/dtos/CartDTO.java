package com.dgomezt.practicaintegradora.entities.dtos;

import com.dgomezt.practicaintegradora.entities.Cart;
import com.dgomezt.practicaintegradora.entities.ProductCartDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    Long id;
    LocalDate creationDate;
    BigDecimal price;
    Long client;
    List<ProductCartDTO> productCartDTOS;

    public CartDTO(Cart cart){
        id = cart.getId();
        creationDate = cart.getCreationDate();
        client = cart.getClient().getId();

        List<ProductCartDTO> productCartDTOList = new ArrayList<>();

        for (ProductCartDetail productCartDetail : cart.getProductCartDetails()) {
            ProductCartDTO productCartDTO = new ProductCartDTO(productCartDetail);
            productCartDTOList.add(productCartDTO);
        }

        productCartDTOS = productCartDTOList;

        price = BigDecimal.valueOf(0);
        for (ProductCartDTO productCartDTO : productCartDTOS) {
            BigDecimal amount = productCartDTO.getPrice().multiply(BigDecimal.valueOf(productCartDTO.getUnits()));
            price = price.add(amount);
        }
    }
}
