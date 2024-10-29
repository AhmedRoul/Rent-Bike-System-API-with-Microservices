package com.RentBikeSystem.UserService.Validation.Imp;

import com.RentBikeSystem.UserService.Validation.ValidEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<ValidEnum, Enum<?>> {
    private Class<? extends Enum<?>> enumClass;
    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(Enum<?> anEnum, ConstraintValidatorContext constraintValidatorContext) {
        if(anEnum==null)
            return false;
        return Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(e -> e.name().equals(anEnum.name()));

    }
}
