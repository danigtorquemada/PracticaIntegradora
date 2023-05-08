package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.entities.Product;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

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
}
