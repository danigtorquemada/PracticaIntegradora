package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.ProductCartDetail;
import com.dgomezt.practicaintegradora.entities.embeddables.ProductCartKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCartDetailRepository extends JpaRepository<ProductCartDetail, ProductCartKey> {
}