package com.dgomezt.practicaintegradora.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidatorEighteenPlusAge.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EighteenPlusAge {
    String message() default "{error.dateEighteen}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
