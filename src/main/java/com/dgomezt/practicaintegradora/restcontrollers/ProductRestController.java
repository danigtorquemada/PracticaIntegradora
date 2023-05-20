package com.dgomezt.practicaintegradora.restcontrollers;

import com.dgomezt.practicaintegradora.entities.Product;
import com.dgomezt.practicaintegradora.entities.dtos.ProductShopDTO;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.ProductRepository;
import com.dgomezt.practicaintegradora.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("product")
public class ProductRestController {
    @Autowired
    ProductService productService;

    @GetMapping("all")
    public ResponseEntity<List<ProductShopDTO>> getAll(){
        List<ProductShopDTO> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("filterByCategory")
    public ResponseEntity<List<ProductShopDTO>> getFilterByCategory(Long[] categories){

        List<ProductShopDTO> products = productService.filterByCategoriesId(List.of(categories));
        return ResponseEntity.ok(products);
    }
    @GetMapping("all/newProducts")
    public ResponseEntity<List<ProductShopDTO>> getNewProducts(){
        return ResponseEntity.ok(productService.newProducts());
    }

    @GetMapping("all/withOffer")
    public ResponseEntity<List<ProductShopDTO>> getProductsWithOffer(){
        return ResponseEntity.ok(productService.productsWithOffer());
    }

    @GetMapping("find/{id}")
    public ResponseEntity<ProductShopDTO> findById(@PathVariable String id) throws ElementNotFoundException {
        return ResponseEntity.ok(productService.findProductShopDTOById(id));
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(Exception e){
        return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
