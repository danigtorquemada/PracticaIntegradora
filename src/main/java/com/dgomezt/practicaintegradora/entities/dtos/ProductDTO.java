package com.dgomezt.practicaintegradora.entities.dtos;

import com.dgomezt.practicaintegradora.entities.Category;
import com.dgomezt.practicaintegradora.entities.Product;
import com.dgomezt.practicaintegradora.validations.CategoryContains;
import com.dgomezt.practicaintegradora.validations.CodeProductRepeat;
import com.dgomezt.practicaintegradora.validations.CollectionContains;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ProductDTO {
    @NotEmpty
    @CodeProductRepeat
    String code;
    @NotEmpty
    String description;
    @NotNull
    @Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "{error.decimal}")
    BigDecimal price;
    @CategoryContains
    Set<Long> categories;
    @NotNull
    Integer stock;
    Integer minSupplierRequest;
    Integer minHiddenStock;
    String offer;
    @Digits(integer = Integer.MAX_VALUE, fraction = 2, message = "{error.decimal}")
    BigDecimal discount;
    String newProduct;
    @NotEmpty
    String brand;
    @NotEmpty
    String model;

    @Min(value = 1, message = "{error.min} 1")
    @Max(value = 5, message = "{error.max} 5")
    Integer evaluation;
    String comments;

    public static ProductDTO fromProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.code = product.getCode();
        productDTO.description = product.getDescription();
        productDTO.price = product.getPrice();
        productDTO.categories = new HashSet<>();

        for (Category category : product.getCategories()) {
            productDTO.categories.add(category.getId());
        }

        productDTO.stock = product.getStock();
        productDTO.minSupplierRequest = product.getMinSupplierRequest();
        productDTO.minHiddenStock = product.getMinHiddenStock();
        productDTO.offer = String.valueOf(product.getOffer());
        productDTO.newProduct = String.valueOf(product.getNewProduct());
        productDTO.evaluation = product.getEvaluation();
        productDTO.brand = product.getBrand();
        productDTO.model = product.getModel();
        productDTO.comments = product.getComments();
        productDTO.discount = product.getDiscount();

        return productDTO;
    }
}
