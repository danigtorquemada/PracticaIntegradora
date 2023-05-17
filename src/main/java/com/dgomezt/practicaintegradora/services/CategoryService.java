package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Category;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    Category findCategoryById(long id) throws ElementNotFoundException;
    List<Category> findAll();
    boolean isPresent(Long id);

    Set<Category> findAllByIds(Set<Long> ids);
}
