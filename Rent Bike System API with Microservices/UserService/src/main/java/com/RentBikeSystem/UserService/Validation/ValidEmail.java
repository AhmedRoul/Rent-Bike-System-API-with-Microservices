package com.RentBikeSystem.UserService.Validation;

import com.RentBikeSystem.UserService.Validation.Imp.EmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface ValidEmail {

    String message() default "Email is exist";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
