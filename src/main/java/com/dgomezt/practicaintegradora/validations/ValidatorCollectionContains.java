package com.dgomezt.practicaintegradora.validations;

import com.dgomezt.practicaintegradora.services.CountryService;
import com.dgomezt.practicaintegradora.services.DocumentTypeService;
import com.dgomezt.practicaintegradora.services.GenderService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidatorCollectionContains implements ConstraintValidator<CollectionContains, String> {
    @Autowired
    CountryService countryService;
    @Autowired
    GenderService genderService;
    @Autowired
    DocumentTypeService documentTypeService;

    CollectionContains.COLLECTIONS collection;
    @Override
    public void initialize(CollectionContains constraintAnnotation) {
        collection = constraintAnnotation.collection();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null) return false;

        switch (collection) {
            case GENDER -> {
                return genderService.isPresentByAbbreviation(s);
            }
            case COUNTRY -> {
                return countryService.isPresentByAbbreviation(s);
            }
            case TYPE_DOCUMENT -> {
                return documentTypeService.isPresentByAbbreviation(s);
            }
        }
        return false;
    }
}