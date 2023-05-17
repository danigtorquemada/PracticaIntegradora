package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.ProductCartDetail;
import com.dgomezt.practicaintegradora.entities.embeddables.ProductCartKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProductCartDetailRepository extends JpaRepository<ProductCartDetail, ProductCartKey> {
    @Transactional
    long deleteByProductCartKey(ProductCartKey productCartKey);
}