package com.dgomezt.practicaintegradora.entities.dtos;

import java.math.BigDecimal;
import java.util.List;

public class ProductDTO {
    Long id;
    String code;
    String description;
    BigDecimal price;
    List<Long> categories;
    Integer stock;
    Integer minSupplierRequest;
    Integer minHiddenStock;
    String offer;
    String newProduct;
    Integer evaluation;
    String brand;
    String model;
    String comments;
}
