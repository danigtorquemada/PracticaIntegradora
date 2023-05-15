package com.dgomezt.practicaintegradora.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternDniNieValidator implements ConstraintValidator<PatternDniNie, Object> {

    private String documentType;
    private String document;

    @Override
    public void initialize(PatternDniNie constraintAnnotation) {
        this.document = constraintAnnotation.document();
        this.documentType = constraintAnnotation.documentType();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object document = new BeanWrapperImpl(o)
                .getPropertyValue(this.document);
        Object documentType = new BeanWrapperImpl(o)
                .getPropertyValue(this.documentType);

        if(documentType == null || document == null) return true;

        if (documentType.equals("D")) {
            Pattern pat = Pattern.compile("[0-9]{7,8}[A-Z a-z]");
            Matcher mat = pat.matcher(document.toString());
            return mat.matches();
        } else if (documentType.equals("N")) {
            Pattern pat = Pattern.compile("^[XYZ]\\d{7,8}[A-Z]$");
            Matcher mat = pat.matcher(document.toString());
            return mat.matches();
        }

        return true;
    }
}
