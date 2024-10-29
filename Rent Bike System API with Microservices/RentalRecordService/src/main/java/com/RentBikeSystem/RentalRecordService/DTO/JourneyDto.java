package com.RentBikeSystem.RentalRecordService.DTO;

import com.RentBikeSystem.RentalRecordService.Entity.StateJounery;
import com.RentBikeSystem.RentalRecordService.Validation.ValidIDS;
import lombok.*;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class JourneyDto {

    private Long id;
    @ValidIDS(FieldName = "user",groups = addJourney.class)
    private Long userId;
    @ValidIDS(FieldName = "bike",groups = addJourney.class)
    private Long bikeId;
    private StateJounery stateJourney;
    @ValidIDS(FieldName = "pointofrental")
    private Long startPointOfRentalId;
    @ValidIDS(FieldName = "pointofrental")
    private Long endPointOfRentalId;
    private LocalDateTime startJourney;
    private LocalDateTime endJourney;
    private float totalHours;

    public interface  addJourney{}


}
