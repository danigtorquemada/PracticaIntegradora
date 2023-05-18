package com.dgomezt.practicaintegradora.restcontrollers;

import com.dgomezt.practicaintegradora.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {
    @Autowired
    ProductRepository productRepository;
}
