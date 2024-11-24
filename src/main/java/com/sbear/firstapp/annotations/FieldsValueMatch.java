package com.sbear.firstapp.annotations;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

import com.sbear.firstapp.validations.FieldsValueMatchValidator;
import jakarta.validation.Payload;

@Constraint(validatedBy = FieldsValueMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(FieldValueMatchers.class)
public @interface FieldsValueMatch {
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String message() default "Field values do not match";
    String field1();
    String field2();
}

