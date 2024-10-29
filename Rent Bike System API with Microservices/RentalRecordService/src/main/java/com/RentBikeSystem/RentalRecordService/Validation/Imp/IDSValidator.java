package com.RentBikeSystem.RentalRecordService.Validation.Imp;

import com.RentBikeSystem.RentalRecordService.API.Client.BikeClient;
import com.RentBikeSystem.RentalRecordService.API.Client.UserClient;
import com.RentBikeSystem.RentalRecordService.Services.JouneryServices;
import com.RentBikeSystem.RentalRecordService.Services.PointOfRentalServices;
import com.RentBikeSystem.RentalRecordService.Validation.ValidIDS;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class IDSValidator implements ConstraintValidator<ValidIDS,Long> {
    @Autowired
    private UserClient userClient;
    @Autowired
    private BikeClient bikeClient;
    @Autowired
    private PointOfRentalServices pointOfRentalServices;

    @Autowired
    private JouneryServices jouneryServices;
    private String NameField;

    /**
     * @param constraintAnnotation
     */
    @Override
    public void initialize(ValidIDS constraintAnnotation) {
        NameField=constraintAnnotation.FieldName();
    }

    /**
     * @param l
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(Long l, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        boolean flag=false;

        switch (NameField)
        {
            case  "user":
                flag= userClient.IsUserExistById(l);

            case  "bike":
                flag=  bikeClient.IsBikeExist(l);

            case "pointofrental":
                flag=  pointOfRentalServices.existPointOfRental(l);

            case  "journey":
                flag=jouneryServices.IsExistJounery(l);
        }

        if(flag)
            return flag;


        constraintValidatorContext.buildConstraintViolationWithTemplate
                        (NameField +"id not exist  pls rewrite valid id")
                .addConstraintViolation();
        return false;
    }

}
