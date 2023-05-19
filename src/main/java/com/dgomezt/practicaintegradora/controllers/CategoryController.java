package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.entities.Category;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("admin/category")
@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/detail/{id}")
    public ModelAndView detailCategory(@PathVariable String id) throws ElementNotFoundException {
        ModelAndView modelAndView = new ModelAndView();

        long categoryId = Long.parseLong(id);

        Category category =categoryService.findCategoryById(categoryId);

        modelAndView.setViewName("main");
        modelAndView.addObject("category", category);
        modelAndView.addObject("content", "category/detail");
        return modelAndView;
    }
}
