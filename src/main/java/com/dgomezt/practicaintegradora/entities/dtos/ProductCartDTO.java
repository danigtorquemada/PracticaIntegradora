package com.dgomezt.practicaintegradora.entities.dtos;

import com.dgomezt.practicaintegradora.entities.Category;
import com.dgomezt.practicaintegradora.entities.ProductCartDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCartDTO {
    Long productId;
    BigDecimal price;
    String description;
    String code;
    List<Long> categories;
    Integer units;

    public ProductCartDTO(ProductCartDetail productCartDetail) {
        productId = productCartDetail.getProduct().getId();
        price = productCartDetail.getProduct().getPrice();
        description = productCartDetail.getProduct().getDescription();
        code = productCartDetail.getProduct().getCode();
        units = productCartDetail.getQuantity();
        categories = new ArrayList<>();

        for (Category category : productCartDetail.getProduct().getCategories()) {
            categories.add(category.getId());
        }
    }
}
