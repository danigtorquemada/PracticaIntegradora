package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Product;
import com.dgomezt.practicaintegradora.entities.UserAdmin;
import com.dgomezt.practicaintegradora.entities.dtos.ProductDTO;
import com.dgomezt.practicaintegradora.entities.dtos.ProductShopDTO;
import com.dgomezt.practicaintegradora.exception.CodeRepeatException;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;

import java.util.List;

public interface ProductService {
    Product findyProductById(long id) throws ElementNotFoundException;
    Product updateProductByDTO(long id, ProductDTO productDTO, UserAdmin userAdmin) throws ElementNotFoundException, CodeRepeatException;
    Product findProductByCode(String code);
    Product save(Product product);
    List<ProductShopDTO> findAll();
    List<ProductShopDTO> filterByCategoriesId(List<Long> categoriesId);
    List<ProductShopDTO> newProducts();
    List<ProductShopDTO> productsWithOffer();
    ProductShopDTO findProductShopDTOById(String id) throws ElementNotFoundException;
}
