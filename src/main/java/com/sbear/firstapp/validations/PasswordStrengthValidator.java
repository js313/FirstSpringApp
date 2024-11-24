package com.sbear.firstapp.validations;

import com.sbear.firstapp.annotations.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {
    List<String> weakPasswords;

    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        weakPasswords = Arrays.asList("12345", "password", "qwerty");
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !weakPasswords.contains(s);
    }
}
