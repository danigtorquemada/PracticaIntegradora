package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.entities.Category;
import com.dgomezt.practicaintegradora.entities.dtos.CategoryDetailsDTO;
import com.dgomezt.practicaintegradora.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/category")
@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/detail/{id}")
    public ModelAndView detailClient(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();

        long categoryId = Long.parseLong(id);

        Category category =categoryService.findCategoryById(categoryId);
        CategoryDetailsDTO categoryDetailsDTO = CategoryDetailsDTO.fromCategory(category);

        modelAndView.setViewName("main");
        modelAndView.addObject("categoryDetailsDTO", categoryDetailsDTO);
        modelAndView.addObject("content", "category/detail");
        return modelAndView;
    }
}
