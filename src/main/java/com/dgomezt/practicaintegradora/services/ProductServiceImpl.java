package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Category;
import com.dgomezt.practicaintegradora.entities.Product;
import com.dgomezt.practicaintegradora.entities.dtos.ProductDTO;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryService categoryService;

    @Override
    public Product findyProductById(long id) throws ElementNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty()) throw new ElementNotFoundException("Product not found");

        return optionalProduct.get();
    }

    @Override
    public Product updateProductByDTO(long id, ProductDTO productDTO) throws ElementNotFoundException {
        Product product = findyProductById(id);
        Set<Category> categories = categoryService.findAllByIds(productDTO.getCategories());
        product.setCategories(categories);

        product.setCode(productDTO.getCode());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setMinSupplierRequest(productDTO.getMinSupplierRequest());
        product.setMinHiddenStock(productDTO.getMinHiddenStock());
        product.setOffer(Boolean.valueOf(productDTO.getOffer()));
        product.setNewProduct(Boolean.valueOf(productDTO.getNewProduct()));
        product.setEvaluation(productDTO.getEvaluation());
        product.setBrand(productDTO.getBrand());
        product.setModel(productDTO.getModel());
        product.setComments(productDTO.getComments());
        product.setDiscount(productDTO.getDiscount());

        return productRepository.save(product);
    }

    @Override
    public Product findProductByCode(String code) {
        return productRepository.findByCode(code);
    }
}
