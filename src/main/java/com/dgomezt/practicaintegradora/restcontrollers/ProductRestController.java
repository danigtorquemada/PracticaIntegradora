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

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("product")
public class ProductRestController {
    @Autowired
    ProductService productService;

    @GetMapping("all")
    public ResponseEntity<List<Product>> getAll(){
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("filterByCategory")
    public ResponseEntity<List<Product>> getFilterByCategory(Long[] categories){

        List<Product> products = productService.filterByCategoriesId(List.of(categories));
        return ResponseEntity.ok(products);
    }
}
