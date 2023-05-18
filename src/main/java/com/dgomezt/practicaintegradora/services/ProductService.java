package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Product;
import com.dgomezt.practicaintegradora.entities.dtos.ProductDTO;
import com.dgomezt.practicaintegradora.exception.CodeRepeatException;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;

import java.util.List;

public interface ProductService {
    Product findyProductById(long id) throws ElementNotFoundException;
    Product updateProductByDTO(long id, ProductDTO productDTO) throws ElementNotFoundException, CodeRepeatException;
    Product findProductByCode(String code);
    Product save(Product product);
    List<Product> findAll();
}
