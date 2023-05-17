package com.dgomezt.practicaintegradora.validations;

import com.dgomezt.practicaintegradora.services.*;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidatorCollectionContains implements ConstraintValidator<CollectionContains, Long> {
    @Autowired
    CountryService countryService;
    @Autowired
    GenderService genderService;
    @Autowired
    DocumentTypeService documentTypeService;
    @Autowired
    TypeRoadService typeRoadService;
    @Autowired
    CategoryService categoryService;

    CollectionContains.COLLECTIONS collection;
    @Override
    public void initialize(CollectionContains constraintAnnotation) {
        collection = constraintAnnotation.collection();
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        if(id == null) return false;

        switch (collection) {
            case GENDER -> {
                return genderService.isPresent(id);
            }
            case COUNTRY -> {
                return countryService.isPresent(id);
            }
            case TYPE_ROAD -> {
                return typeRoadService.isPresent(id);
            }
            case TYPE_DOCUMENT -> {
                return documentTypeService.isPresent(id);
            }
            case CATEGORY -> {
                return categoryService.isPresent(id);
            }
        }
        return false;
    }
}