package com.dgomezt.practicaintegradora.validations;

import com.dgomezt.practicaintegradora.exception.ElementNotFoundException;
import com.dgomezt.practicaintegradora.services.CategoryService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class ValidatorCategoryContains implements ConstraintValidator<CategoryContains, Set<Long>> {
    @Autowired
    CategoryService categoryService;

    @Override
    public void initialize(CategoryContains constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Set<Long> longSet, ConstraintValidatorContext constraintValidatorContext) {
        for (Long aLong : longSet) {
            try {
                categoryService.findCategoryById(aLong);
            } catch (ElementNotFoundException e) {
                return false;
            }
        }
        return true;
    }
}
