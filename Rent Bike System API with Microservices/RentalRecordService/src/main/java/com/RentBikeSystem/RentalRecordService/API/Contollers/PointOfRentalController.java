package com.RentBikeSystem.RentalRecordService.API.Contollers;

import com.RentBikeSystem.RentalRecordService.DTO.JourneyDto;
import com.RentBikeSystem.RentalRecordService.DTO.PointOfRentalDto;
import com.RentBikeSystem.RentalRecordService.Services.PointOfRentalServices;
import com.RentBikeSystem.RentalRecordService.Validation.ValidNames;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/PointOfRental/")
public class PointOfRentalController {
    @Autowired
    PointOfRentalServices pointOfRentalServices;

    @GetMapping("mostpopular")
    public ResponseEntity<?> getMostPopular()
    {
       return ResponseEntity.ok( pointOfRentalServices.getMostPopularPointOfRental());
    }
    @GetMapping("mostpopular/city/{city}")
    public ResponseEntity<?> getMostPopular(@ValidNames(nameField = "City")@PathVariable String city)
    {
        return ResponseEntity.ok( pointOfRentalServices.getMostPopularPointOfRental(city));
    }
    @GetMapping("isexist/{pointofrentalid}")
    public ResponseEntity<?> IsExist(@PathVariable Long pointofrentalid)
    {
        return ResponseEntity.ok( pointOfRentalServices.existPointOfRental(pointofrentalid));
    }

    @PostMapping
    public  ResponseEntity<?> AddPointOfRent(@Validated(PointOfRentalDto.insert.class)
                                                 @RequestBody PointOfRentalDto pointOfRentalDto)
    {
        try {
            PointOfRentalDto saved = pointOfRentalServices.createPointOfRental(pointOfRentalDto);
            if (saved != null)
                return ResponseEntity.ok(saved);

            return ResponseEntity.badRequest().body("Unable to save point Of Rental: Internal server error.");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred: " + e.getMessage());
        }
    }
    @GetMapping("{pointofrentalid}")
    public ResponseEntity<?> getPointOfRental(@PathVariable Long pointofrentalid)
    {
        PointOfRentalDto dto= pointOfRentalServices.getPointOfRentalById(pointofrentalid);
        if(pointOfRentalServices.getPointOfRentalById(pointofrentalid)!=null)
            return ResponseEntity.ok(dto);

        return ResponseEntity.badRequest().body("pointofrentalid  not exist !!");

    }
    @GetMapping
    public ResponseEntity<?> getPointOfRental()
    {
        return ResponseEntity.ok( pointOfRentalServices.getAllPointsOfRental());
    }
    @DeleteMapping("{pointofrentalid}")
    public ResponseEntity<?> deletePointOfRental(@PathVariable Long pointofrentalid)
    {
        if(pointOfRentalServices.deletePointOfRental(pointofrentalid)) {
            return ResponseEntity.ok("Deleted Done ");
        }
        return ResponseEntity.badRequest().body("point of rental id not exist !!");
    }
    @GetMapping("city/{city}")
    public ResponseEntity<?> getPointOfRentalByCity(@ValidNames(nameField = "City") @PathVariable String city)
    {
        return ResponseEntity.ok( pointOfRentalServices.getPointsOfRentalByCity(city));
    }
    @GetMapping("/search")
    public List<PointOfRentalDto> searchPointsOfRental(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String nameArea) {

        // If only city is provided
        if (city != null && address == null && nameArea == null) {
            return pointOfRentalServices.getPointsOfRentalSearchByCity(city);
        }
        // If only address is provided
        else if (city == null && address != null && nameArea == null) {
            return pointOfRentalServices.getPointsOfRentalSearchByAddress(address);
        }
        // If only nameArea is provided
        else if (city == null && address == null && nameArea != null) {
            return pointOfRentalServices.getPointsOfRentalSearchByNameArea(nameArea);
        }
        // If both city and address are provided
        else if (city != null && address != null && nameArea == null) {
            return pointOfRentalServices.getPointsOfRentalSearchByAddressAndCity(city, address);
        }

        else {
            return List.of();
        }
    }
    @GetMapping("/nearlocation/{radiusinkm}")
    public List<PointOfRentalDto> searchPointsOfRental(@Size(min = 1, max = 100, message = "The value must be between 0 and 150.")
                                                           @PathVariable float radiusinkm ,
                                                       @Validated(PointOfRentalDto.nearLocation.class)
                                                       @RequestBody PointOfRentalDto pointOfRentalDto){
        return pointOfRentalServices.getPointsOfRentalNearLocation
                (pointOfRentalDto.getLatitude(), pointOfRentalDto.getLongitude(),radiusinkm);

    }

}
