package com.dgomezt.practicaintegradora.repositories;

import com.dgomezt.practicaintegradora.entities.ProductOrderDetails;
import com.dgomezt.practicaintegradora.entities.embeddables.ProductOrderKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderDetailsRepository extends JpaRepository<ProductOrderDetails, ProductOrderKey> {
}