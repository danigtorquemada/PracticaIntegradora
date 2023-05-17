package com.dgomezt.practicaintegradora.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidatorCodeProductRepeat.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CodeProductRepeat {
    String message() default "{error.valueExist}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
