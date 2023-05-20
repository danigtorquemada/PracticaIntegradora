package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByCode(String code);
    List<Product> findByCategories_IdIn(Collection<Long> ids);
    List<Product> findByNewProductTrue();
    List<Product> findByOfferTrue();
}