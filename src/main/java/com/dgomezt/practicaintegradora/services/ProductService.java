package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Product;
import com.dgomezt.practicaintegradora.entities.dtos.ProductDTO;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;

public interface ProductService {
    Product findyProductById(long id) throws ElementNotFoundException;
    Product updateProductByDTO(long id, ProductDTO productDTO) throws ElementNotFoundException;
    Product findProductByCode(String code);
}
