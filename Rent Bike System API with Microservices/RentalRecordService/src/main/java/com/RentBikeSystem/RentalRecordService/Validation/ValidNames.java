package com.RentBikeSystem.RentalRecordService.Validation;

import com.RentBikeSystem.RentalRecordService.Validation.Imp.NameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NameValidator.class )
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidNames {

    String nameField() default "name ";
    short  minLen() default 3;
    short  maxLen() default 101;
    String message() default "invalid name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
