package com.dgomezt.practicaintegradora.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = PatternDniNieValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PatternDniNie {
    String message() default "{error.patternDNINIE}";
    
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String document();

    String documentType();
}
