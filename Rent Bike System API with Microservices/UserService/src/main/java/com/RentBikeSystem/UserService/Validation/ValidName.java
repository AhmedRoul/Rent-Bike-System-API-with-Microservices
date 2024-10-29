package com.RentBikeSystem.UserService.Validation;

import com.RentBikeSystem.UserService.Validation.Imp.NameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Constraint(validatedBy = NameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidName {
    String message() default "invalid name";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
