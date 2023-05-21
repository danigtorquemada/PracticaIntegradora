package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.ProductOrderDetails;
import com.dgomezt.practicaintegradora.repositories.ProductOrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductOrderDetailServiceImpl implements ProductOrderDetailService{
    @Autowired
    ProductOrderDetailsRepository productOrderDetailsRepository;
    @Override
    public ProductOrderDetails save(ProductOrderDetails productOrderDetails) {
        return productOrderDetailsRepository.save(productOrderDetails);
    }
}
