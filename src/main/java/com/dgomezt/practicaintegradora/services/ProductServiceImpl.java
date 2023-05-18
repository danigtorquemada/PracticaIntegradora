package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Category;
import com.dgomezt.practicaintegradora.entities.Product;
import com.dgomezt.practicaintegradora.entities.Warning;
import com.dgomezt.practicaintegradora.entities.dtos.ProductDTO;
import com.dgomezt.practicaintegradora.exception.CodeRepeatException;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.ProductRepository;
import com.dgomezt.practicaintegradora.repositories.WarningLevelRepository;
import com.dgomezt.practicaintegradora.repositories.WarningRepository;
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
    @Autowired
    WarningRepository warningRepository;
    @Autowired
    WarningLevelRepository warningLevelRepository;

    @Override
    public Product findyProductById(long id) throws ElementNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty()) throw new ElementNotFoundException("Product not found");

        return optionalProduct.get();
    }

    @Override
    public Product updateProductByDTO(long id, ProductDTO productDTO) throws ElementNotFoundException, CodeRepeatException {
        Product product = productRepository.findByCode(productDTO.getCode());
        if (product != null && product.getId() != id)
            throw new CodeRepeatException("Code repeated");

        product = findyProductById(id);
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

        return save(product);
    }

    @Override
    public Product findProductByCode(String code) {
        return productRepository.findByCode(code);
    }

    @Override
    public Product save(Product product) {
        Warning newWarning = new Warning();
        newWarning.setProduct(product);
        newWarning.setProductStock(product.getStock());

        if(product.getStock() < product.getMinSupplierRequest()){
            newWarning.setDescription("Product " + product.getCode() + " id " + product.getId() + " need supplier order.");
            newWarning.setWarningLevel(warningLevelRepository.findById(2L).get());
            warningRepository.save(newWarning);
        }

        if(product.getStock() < product.getMinHiddenStock()){
            newWarning.setDescription("Product " + product.getCode() + " id " + product.getId() + " need be hidden.");
            newWarning.setWarningLevel(warningLevelRepository.findById(3L).get());
            warningRepository.save(newWarning);

            product.setHidden(true);
        }else if(product.getHidden())
            product.setHidden(false);

        return productRepository.save(product);
    }
}
