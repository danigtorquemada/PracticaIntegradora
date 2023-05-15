package com.dgomezt.practicaintegradora.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidatorCollectionContains.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CollectionContains {
    COLLECTIONS collection();
    enum COLLECTIONS{GENDER, COUNTRY, TYPE_DOCUMENT}

    String message() default "{error.collection}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
