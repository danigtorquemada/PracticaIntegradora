package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Category;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category findCategoryById(long id) throws ElementNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if(optionalCategory.isEmpty()) throw new ElementNotFoundException("Category not found");

        return optionalCategory.get();
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
