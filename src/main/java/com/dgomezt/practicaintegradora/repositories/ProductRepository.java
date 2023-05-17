package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByCode(String code);
}