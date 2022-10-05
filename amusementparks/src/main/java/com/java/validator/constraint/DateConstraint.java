package com.java.validator.constraint;

import com.java.validator.DateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface DateConstraint {
  String format();
  String message() default "Invalid date format.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
