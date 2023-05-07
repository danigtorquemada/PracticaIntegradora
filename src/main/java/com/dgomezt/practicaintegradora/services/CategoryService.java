package com.dgomezt.practicaintegradora.services;

import com.dgomezt.practicaintegradora.entities.Category;
import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;

public interface CategoryService {
    Category findCategoryById(long id) throws ElementNotFoundException;
}
