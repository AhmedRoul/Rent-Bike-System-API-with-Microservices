package com.RentBikeSystem.RentalRecordService.Validation.Imp;

import com.RentBikeSystem.RentalRecordService.Validation.ValidLocation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;
import java.util.Objects;

public class LocationValidator implements ConstraintValidator<ValidLocation, BigDecimal> {

    private  String Name;

    /**
     * @param constraintAnnotation
     */
    @Override
    public void initialize(ValidLocation constraintAnnotation) {
        this.Name=constraintAnnotation.nameField();
    }

    /**
     * @param bigDecimal
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(BigDecimal bigDecimal, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();

        String lenError="";
        StringBuilder stringBuilder=new StringBuilder("[");
        if(Objects.equals(Name, "latitude"))
            lenError ="\"Invalid your input :Latitude  must be greater than or equal to -90.0  and " +
                " Latitude  must be less than or equal to 90.0 \" ]";


        else if (Objects.equals(Name, "longitude")) {
            lenError ="\"Invalid your input :Longitude   must be greater than or equal to -180.0  and " +
                    " Longitude   must be less than or equal to 180.0 \" ]";
        }
        if(bigDecimal==null)
        {
            stringBuilder.append("\"+name+\" cannot be null \", ");
            stringBuilder.append(lenError);
            constraintValidatorContext.buildConstraintViolationWithTemplate(stringBuilder.toString())
                    .addConstraintViolation();
            return false;
        }
        boolean flag=true;
        if(Objects.equals(Name, "latitude")){
            if (bigDecimal.compareTo(BigDecimal.valueOf(-90))< 0 && bigDecimal.compareTo(BigDecimal.valueOf(90))< 0) {
                stringBuilder.append(lenError);
                constraintValidatorContext.buildConstraintViolationWithTemplate(stringBuilder.toString())
                        .addConstraintViolation();
                flag=false;
            }

        }

        else if (Objects.equals(Name, "longitude"))
        {
            if (bigDecimal.compareTo(BigDecimal.valueOf(-180))< 0 && bigDecimal.compareTo(BigDecimal.valueOf(180))< 0) {
                stringBuilder.append(lenError);
                constraintValidatorContext.buildConstraintViolationWithTemplate(stringBuilder.toString())
                        .addConstraintViolation();
                flag=false;
            }

        }
        return flag;
    }
}
