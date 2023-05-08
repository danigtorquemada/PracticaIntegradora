package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Product;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;

public interface ProductService {
    Product findyProductById(long id) throws ElementNotFoundException;
}
