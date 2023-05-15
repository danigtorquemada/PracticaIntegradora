package com.dgomezt.practicaintegradora.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class ValidatorEighteenPlusAge implements ConstraintValidator<EighteenPlusAge, LocalDate> {

    @Override
    public void initialize(EighteenPlusAge constraintAnnotation) {

    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        try {
            LocalDate now = LocalDate.now();
            Period period = date.until(now);
            int yearsBetween = period.getYears();
            return yearsBetween >= 18;
        } catch (Exception e) {
            return false;
        }
    }
}
