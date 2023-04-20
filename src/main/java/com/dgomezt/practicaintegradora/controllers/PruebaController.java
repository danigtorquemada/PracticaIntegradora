package com.dgomezt.practicaintegradora.controllers;

import com.dgomezt.practicaintegradora.entities.Category;
import com.dgomezt.practicaintegradora.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PruebaController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/pruebaListado")
    public ModelAndView pruebaListado(){
        List<Category> categories = categoryRepository.findAll();

        for (Category category : categories) {
            System.out.println(category);
        }

        return new ModelAndView("prueba");
    }

    @GetMapping("/pruebaInsertar")
    public ModelAndView pruebaInsertar(){
        Category prueba = new Category();
        prueba.setMaxSpend(20);
        prueba.setMinSpend(10);

        System.out.println(categoryRepository.save(prueba));

        return new ModelAndView("prueba");
    }

    @GetMapping("/pruebaBorrado")
    public ModelAndView pruebaBorrado(){
        Category prueba = new Category();
        prueba.setId(1L);
        prueba.setMaxSpend(20);
        prueba.setMinSpend(10);

        categoryRepository.delete(prueba);

        return new ModelAndView("prueba");
    }
}
