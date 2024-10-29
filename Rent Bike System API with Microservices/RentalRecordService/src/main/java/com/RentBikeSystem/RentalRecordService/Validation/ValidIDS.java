package com.RentBikeSystem.RentalRecordService.Validation;

import com.RentBikeSystem.RentalRecordService.Validation.Imp.IDSValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD ,ElementType.PARAMETER})
@Constraint(validatedBy = IDSValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIDS {

    String FieldName() default "";


    String message() default "invalid name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
