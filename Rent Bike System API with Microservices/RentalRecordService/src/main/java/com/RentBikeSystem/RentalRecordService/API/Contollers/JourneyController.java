package com.RentBikeSystem.RentalRecordService.API.Contollers;

import com.RentBikeSystem.RentalRecordService.DTO.JourneyDto;
import com.RentBikeSystem.RentalRecordService.Entity.StateJounery;
import com.RentBikeSystem.RentalRecordService.Services.JouneryServices;
import com.RentBikeSystem.RentalRecordService.Validation.ValidIDS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/journey/")
public class JourneyController {

    @Autowired
    JouneryServices jouneryServices;

    @PostMapping
    public ResponseEntity<?> AddJourney(@Validated(JourneyDto.addJourney.class) @RequestBody JourneyDto journeyDto)
    {
        try {
            JourneyDto dto=JourneyDto.builder()
                    .userId(journeyDto.getUserId())
                    .bikeId(journeyDto.getBikeId())
                    .stateJourney(StateJounery.WaitingPayment)
                    .build();
            JourneyDto saved = jouneryServices.createJourney(dto);
            if (saved != null)
                return ResponseEntity.ok(saved);

            return ResponseEntity.badRequest().body("Unable to save journey: Internal server error.");
        }
        catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + e.getMessage());
        }
    }
    @PatchMapping("journeyid/{journeyId}/startjourney/{id}")
    public ResponseEntity<?> startJourney(@ValidIDS(FieldName = "journey")@PathVariable Long journeyId){

        if(jouneryServices.startJourney(journeyId)){

            return  ResponseEntity.ok(true);
        }
        return ResponseEntity.badRequest().body("Unable to update changes journey: Internal server error.");

    }
    @PatchMapping("completejourney/journeyid/{journeyId}/endpointid/{endpointid}")
    public ResponseEntity<?> completeJourney(
            @ValidIDS(FieldName = "journey") @PathVariable Long journeyId
            ,@ValidIDS(FieldName = "pointofrental")@PathVariable Long endpointid){

        if(jouneryServices.completeJourney(journeyId,endpointid)){
            return  ResponseEntity.ok(true);

        }
        return ResponseEntity.badRequest().body("Unable to update changes journey: Internal server error.");

    }
    @GetMapping("completejourney/user/{userid}")
    public boolean IsCompleted(@ValidIDS(FieldName = "user") @PathVariable Long userid){

       return jouneryServices.IsCompletedJourney(userid);
    }
    @PatchMapping("canceljourney/journeyid/{journeyId}/endpointid/{endpointid}")
    public ResponseEntity<?> cancelJourney(
            @ValidIDS(FieldName = "journey")@PathVariable Long journeyId,
            @ValidIDS(FieldName = "pointofrental") @PathVariable Long endpointid){

        if(jouneryServices.cancelJourney(journeyId,endpointid)){
            return  ResponseEntity.ok(true);

        }
        return ResponseEntity.badRequest().body("Unable to update changes journey: Internal server error.");
    }
    @GetMapping("{journeyId}")
    public ResponseEntity<?> getJourney(@ValidIDS(FieldName = "journey")@PathVariable Long journeyId){
        JourneyDto dto =jouneryServices.getJourneyById(journeyId);
        if(dto!=null)
            return  ResponseEntity.ok(dto);
        else
            return ResponseEntity.badRequest().body("journeyId  not exist");
    }
    @GetMapping("journey/all/user/{userId}")
    public ResponseEntity<?> getJourneysByUser(@ValidIDS(FieldName = "user")@PathVariable Long userId){
        List<JourneyDto> list =jouneryServices.getJourneysByUser(userId);
        return  ResponseEntity.ok(list);
    }
    @GetMapping("journey/active/user/{userId}")
    public ResponseEntity<?> getJourneyByUser(@ValidIDS(FieldName = "user")@PathVariable Long userId){
        JourneyDto journey =jouneryServices.getJourneyByUser(userId);
        if(journey!=null)
            return  ResponseEntity.ok(journey);

        return ResponseEntity.badRequest().body("no active journey for user : "+userId);
    }
    @GetMapping("journey/active")
    public ResponseEntity<?> getJourneys(){
        List<JourneyDto> list =jouneryServices.getActiveJourneys();
        return  ResponseEntity.ok(list);
    }
    @GetMapping("journey/active/hour/{hours}")
    public ResponseEntity<?> getJourneysByHours(float hours){
        List<JourneyDto> list =jouneryServices.getActiveJourneys(hours);
        return  ResponseEntity.ok(list);
    }
    @GetMapping("journey/active/hour/{hours}/pages")
    public ResponseEntity<?> getJourneysByHours(
            @RequestParam(defaultValue = "0") final Integer pageNumber,
            @RequestParam(defaultValue = "5") final Integer size,
            float hours){

        Pageable pageable=PageRequest.of(pageNumber, size);
        List<JourneyDto> list =jouneryServices.getActiveJourneys(hours,pageable);
        return  ResponseEntity.ok(list);
    }
    @GetMapping("journey/all/bike/{bikeid}")
    public ResponseEntity<?> getJourneysByBike(@ValidIDS(FieldName = "bike")@PathVariable Long bikeid){
        List<JourneyDto> list =jouneryServices.getJourneysByBike(bikeid);
        return  ResponseEntity.ok(list);
    }
    @GetMapping("user/all/bike/{bikeid}")
    public ResponseEntity<?> getUserUseBike(@ValidIDS(FieldName = "bike")@PathVariable Long bikeid){
        List<Long> list =jouneryServices.getUsersByBike(bikeid);
        return  ResponseEntity.ok(list);
    }


}
