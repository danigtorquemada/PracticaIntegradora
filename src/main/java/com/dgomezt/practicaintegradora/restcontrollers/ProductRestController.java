package com.dgomezt.practicaintegradora.restcontrollers;

import com.dgomezt.practicaintegradora.entities.Product;
import com.dgomezt.practicaintegradora.repositories.ProductRepository;
import com.dgomezt.practicaintegradora.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductRestController {
    @Autowired
    ProductService productService;

    @CrossOrigin
    @GetMapping("all")
    public ResponseEntity<List<Product>> getAll(){
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }
}
