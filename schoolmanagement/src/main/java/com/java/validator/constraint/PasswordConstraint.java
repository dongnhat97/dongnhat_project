package com.java.validator.constraint;

import com.java.validator.DateValidator;
import com.java.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordConstraint {
    String format();
    String message() default "password in valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
