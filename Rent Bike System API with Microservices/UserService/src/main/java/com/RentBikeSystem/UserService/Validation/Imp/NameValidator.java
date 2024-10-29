package com.RentBikeSystem.UserService.Validation.Imp;

import com.RentBikeSystem.UserService.Validation.ValidName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Size;

public class NameValidator implements ConstraintValidator< ValidName,String> {

    @Override
    public void initialize(ValidName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        constraintValidatorContext.disableDefaultConstraintViolation();
        boolean Valid=true;
        StringBuilder error=new StringBuilder("[");
        if(!(s.length()>2&&s.length()<21)){
            error.append("\" Name must be at least 3 characters long, at most 20 characters\"  ,");
            Valid=false;
        }
        if(!s.matches("^[a-zA-Z]+")){
            error.append("\"English characters only\"  ");
            Valid=false;
        }
        error.append("]");
        constraintValidatorContext.buildConstraintViolationWithTemplate(String.valueOf(error))
                .addConstraintViolation();

        return Valid;
    }
}
