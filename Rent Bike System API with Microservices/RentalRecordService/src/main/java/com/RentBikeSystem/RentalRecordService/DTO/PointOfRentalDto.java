package com.RentBikeSystem.RentalRecordService.DTO;

import com.RentBikeSystem.RentalRecordService.Validation.ValidLocation;
import com.RentBikeSystem.RentalRecordService.Validation.ValidNames;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointOfRentalDto {
    private Long id;
    @ValidNames(nameField = "City",groups = insert.class)
    private String city;
    @ValidNames(nameField = "Address" ,minLen = 5,maxLen = 255,groups = insert.class)
    private String address;

    @ValidLocation(nameField = "latitude",groups = {insert.class ,nearLocation.class})

    private BigDecimal  latitude;
    @ValidLocation(nameField = "longitude",groups = {insert.class ,nearLocation.class})
    private BigDecimal longitude;

    @ValidNames(nameField = "nameArea",groups = insert.class)
    private String nameArea;
    public  interface insert{}
    public  interface nearLocation{}

}
