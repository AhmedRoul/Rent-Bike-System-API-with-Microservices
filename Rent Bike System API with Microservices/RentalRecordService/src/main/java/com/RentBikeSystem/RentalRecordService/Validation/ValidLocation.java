package com.RentBikeSystem.RentalRecordService.Validation;

import com.RentBikeSystem.RentalRecordService.Validation.Imp.LocationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD ,ElementType.PARAMETER})
@Constraint(validatedBy = LocationValidator.class )
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLocation {
    String nameField() default "no exist! ";

    String message() default "invalid location!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
