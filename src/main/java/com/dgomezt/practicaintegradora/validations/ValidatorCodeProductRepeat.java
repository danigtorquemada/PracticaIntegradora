package com.dgomezt.practicaintegradora.validations;

import com.dgomezt.practicaintegradora.services.ProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidatorCodeProductRepeat implements ConstraintValidator<CodeProductRepeat, String> {
    @Autowired
    ProductService productService;

    @Override
    public void initialize(CodeProductRepeat constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {
        return productService.findProductByCode(code) == null;
    }
}
