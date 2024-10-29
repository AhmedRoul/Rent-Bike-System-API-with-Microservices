package com.RentBikeSystem.RentalRecordService.Validation.Imp;

import com.RentBikeSystem.RentalRecordService.Validation.ValidNames;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<ValidNames, String> {

    private String name;
    private  short minLen;
    private  short maxLen;

    /**
     * @param constraintAnnotation
     */
    @Override
    public void initialize(ValidNames constraintAnnotation) {
        this.name=constraintAnnotation.nameField();
        this.maxLen=constraintAnnotation.minLen();
        this.minLen=constraintAnnotation.maxLen();
    }

    /**
     * @param s
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();

        String lenError="\"Invalid your input :  %s  must be at least %d characters and at most %d characters ,Rewrite Again\" ]"
                .formatted(name ,minLen,maxLen);

        StringBuilder stringBuilder=new StringBuilder("[");

        if(s==null) {
            stringBuilder.append("\""+name+" cannot be null \", ");
            stringBuilder.append(lenError);
            constraintValidatorContext.buildConstraintViolationWithTemplate(stringBuilder.toString())
                    .addConstraintViolation();
            return false;
        }
        if(!(s.length()>minLen&&s.length()<maxLen)){
            stringBuilder.append(lenError);
            constraintValidatorContext.buildConstraintViolationWithTemplate(stringBuilder.toString())
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
