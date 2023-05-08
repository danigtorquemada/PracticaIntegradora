package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Product;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product findyProductById(long id) throws ElementNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty()) throw new ElementNotFoundException("Product not found");

        return optionalProduct.get();
    }
}
