package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Category;
import com.dgomezt.practicaintegradora.entities.Product;
import com.dgomezt.practicaintegradora.entities.UserAdmin;
import com.dgomezt.practicaintegradora.entities.Warning;
import com.dgomezt.practicaintegradora.entities.dtos.ProductDTO;
import com.dgomezt.practicaintegradora.entities.dtos.ProductShopDTO;
import com.dgomezt.practicaintegradora.exception.CodeRepeatException;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    WarningService warningService;
    @Autowired
    WarningLevelService warningLevelService;

    @Override
    public Product findyProductById(long id) throws ElementNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty()) throw new ElementNotFoundException("Product not found");

        return optionalProduct.get();
    }

    @Override
    public Product updateProductByDTO(long id, ProductDTO productDTO, UserAdmin userAdmin) throws ElementNotFoundException, CodeRepeatException {
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
        product.getAuditory().setLastModificationDate(LocalDate.now());
        product.getAuditory().setLastModificationUser(userAdmin);

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
        newWarning.setCreationDate(LocalDate.now());

        if(product.getStock() < product.getMinSupplierRequest()){
            newWarning.setDescription("Product " + product.getCode() + " id " + product.getId() + " need supplier order.");
            newWarning.setWarningLevel(warningLevelService.findById(2L));
            warningService.save(newWarning);
        }

        if(product.getStock() < product.getMinHiddenStock()){
            newWarning.setDescription("Product " + product.getCode() + " id " + product.getId() + " need be hidden.");
            newWarning.setWarningLevel(warningLevelService.findById(3L));
            warningService.save(newWarning);

            product.setHidden(true);
        }else if(product.getHidden())
            product.setHidden(false);

        return productRepository.save(product);
    }

    @Override
    public List<ProductShopDTO> findAll() {
        List<ProductShopDTO> productShopDTOS = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            productShopDTOS.add(new ProductShopDTO(product));
        }

        return productShopDTOS;
    }

    @Override
    public List<ProductShopDTO> filterByCategoriesId(List<Long> categoriesId) {
        List<ProductShopDTO> productShopDTOS = new ArrayList<>();
        for (Product product : productRepository.findByCategories_IdIn(categoriesId)) {
            productShopDTOS.add(new ProductShopDTO(product));
        }
        return productShopDTOS;
    }

    @Override
    public List<ProductShopDTO> newProducts() {
        List<ProductShopDTO> productShopDTOS = new ArrayList<>();
        for (Product product : productRepository.findByNewProductTrue()) {
            productShopDTOS.add(new ProductShopDTO(product));
        }
        return productShopDTOS;
    }

    @Override
    public List<ProductShopDTO> productsWithOffer() {
        List<ProductShopDTO> productShopDTOS = new ArrayList<>();
        for (Product product : productRepository.findByOfferTrue()) {
            productShopDTOS.add(new ProductShopDTO(product));
        }
        return productShopDTOS;
    }
}
