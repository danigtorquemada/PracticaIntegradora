package com.dgomezt.practicaintegradora.entities.dtos;

import com.dgomezt.practicaintegradora.entities.Category;
import com.dgomezt.practicaintegradora.entities.Product;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ProductShopDTO {
    Long id;
    String code;
    String description;
    BigDecimal price;
    List<Long> categories;
    Boolean offer;
    BigDecimal discount;
    Boolean newProduct;
    String brand;
    String model;
    String comments;
    Integer evaluation;

    public ProductShopDTO(Product product) {
        id = product.getId();
        code = product.getCode();
        description = product.getDescription();
        price = product.getPrice();

        List<Long> categories = new ArrayList<>();
        for (Category category : product.getCategories()) {
            categories.add(category.getId());
        }
        this.categories = categories;
        offer = product.getOffer();
        discount = product.getDiscount();
        newProduct = product.getNewProduct();
        brand = product.getBrand();
        model = product.getModel();
        comments = product.getComments();
        evaluation = product.getEvaluation();
    }
}
