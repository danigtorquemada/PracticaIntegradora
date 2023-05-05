package com.dgomezt.practicaintegradora.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class MatchValuesValidator implements ConstraintValidator<MatchValues, Object> {
    private String field;
    private String fieldMatch;

    public void initialize(MatchValues constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    public boolean isValid(Object value,
                           ConstraintValidatorContext constraintValidatorContext) {

        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(fieldMatch);

        if (fieldValue != null && fieldMatchValue != null)
            return fieldValue.equals(fieldMatchValue);

        return false;
    }
}
