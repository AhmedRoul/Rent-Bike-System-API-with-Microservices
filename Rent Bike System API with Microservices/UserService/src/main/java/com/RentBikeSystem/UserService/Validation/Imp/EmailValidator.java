package com.RentBikeSystem.UserService.Validation.Imp;

import com.RentBikeSystem.UserService.Services.Imp.UserServiceImp;
import com.RentBikeSystem.UserService.Services.UserService;
import com.RentBikeSystem.UserService.Validation.ValidEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public  class EmailValidator implements ConstraintValidator<ValidEmail ,String> {

    @Autowired
    UserService userService;


    @Override
    public void initialize(ValidEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String Email, ConstraintValidatorContext constraintValidatorContext) {

        constraintValidatorContext.disableDefaultConstraintViolation();
        boolean Valid=true;
        StringBuilder error=new StringBuilder("[");

        if (userService.IsExist(Email)){
            error.append("\"Email is exist ,pls change\",");
            Valid=false;
        }
        if(!Email.matches("^[a-zA-Z0-9._%±]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$")){
            error.append("\"Email isValid format.\"");
            Valid=false;
        }
        error.append("]");
        constraintValidatorContext.buildConstraintViolationWithTemplate(String.valueOf(error))
                .addConstraintViolation();

        return Valid;
    }
}
