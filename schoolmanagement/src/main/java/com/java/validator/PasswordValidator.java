package com.java.validator;

import com.java.validator.constraint.PasswordConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint,String> {
    String format;

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
        this.format=constraintAnnotation.format();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value!=null&& value.matches(format);
    }
}
