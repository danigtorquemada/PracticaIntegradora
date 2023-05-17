package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.entities.Category;
import com.dgomezt.practicaintegradora.entities.Product;
import com.dgomezt.practicaintegradora.entities.dtos.ProductDTO;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.services.CategoryService;
import com.dgomezt.practicaintegradora.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/detail/{id}")
    public ModelAndView detailProduct(@PathVariable String id) throws ElementNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        long productId = Long.parseLong(id);

        Product product = productService.findyProductById(productId);

        modelAndView.setViewName("main");
        modelAndView.addObject("product", product);
        modelAndView.addObject("content", "product/detail");
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateProductView(@PathVariable String id) throws ElementNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        long productId = Long.parseLong(id);

        Product product = productService.findyProductById(productId);
        ProductDTO productDTO = ProductDTO.fromProduct(product);
        List<Category> categoryList = categoryService.findAll();

        modelAndView.setViewName("main");
        modelAndView.addObject("productDTO", productDTO);
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("content", "product/update");
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateProduct(@PathVariable String id, @Valid ProductDTO productDTO, BindingResult bindingResult) throws ElementNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        if(!bindingResult.hasErrors()){
            long productId = Long.parseLong(id);

            Product product = productService.updateProductByDTO(productId, productDTO);

            modelAndView.setViewName("redirect:/product/detail/" + product.getId());
            return modelAndView;
        }

        List<Category> categoryList = categoryService.findAll();

        modelAndView.setViewName("main");
        modelAndView.addObject("productDTO", productDTO);
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("content", "product/update");
        return modelAndView;
    }
}
