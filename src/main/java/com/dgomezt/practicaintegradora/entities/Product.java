package com.dgomezt.practicaintegradora.entities;

import com.dgomezt.practicaintegradora.entities.helpers.Auditory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "price", precision = 19, scale = 2)
    private BigDecimal price;

    @Column(name = "total_sales_amount", precision = 19, scale = 2)
    private Integer totalSalesAmount;

    @Column(name = "total_sales_revenue")
    private BigDecimal totalSalesRevenue;

    @ManyToMany
    @JoinTable(name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_productCategories_product")),
            inverseJoinColumns = @JoinColumn(name = "categories_id", foreignKey = @ForeignKey(name = "FK_productCategories_category")))
    private Set<Category> categories = new LinkedHashSet<>();

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "min_supplier_request")
    private Integer minSupplierRequest;

    @Column(name = "min_hidden_stock")
    private Integer minHiddenStock;

    @Column(name = "offer")
    private Boolean offer;

    @Column(name = "discount", precision = 19, scale = 2)
    private BigDecimal discount;

    @Column(name = "new_product")
    private Boolean newProduct;

    @Column(name = "evaluation")
    private Integer evaluation;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "comments")
    private String comments;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "auditory_id", foreignKey = @ForeignKey(name = "FK_product_auditory"))
    private Auditory auditory;


}