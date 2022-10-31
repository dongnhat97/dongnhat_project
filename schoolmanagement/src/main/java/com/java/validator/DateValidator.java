package com.java.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.java.validator.constraint.DateConstraint;

public class DateValidator implements ConstraintValidator<DateConstraint, String> {

    private String format;

    @Override
    public void initialize(DateConstraint constraintAnnotation) {
        this.format = constraintAnnotation.format();
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches(format);
    }
}