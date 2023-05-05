package com.dgomezt.practicaintegradora.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MatchValuesValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface MatchValues {
    String message() default "";

    String field();

    String fieldMatch();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        MatchValues[] value();
    }
}
