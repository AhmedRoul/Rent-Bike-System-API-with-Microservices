package com.RentBikeSystem.BikeService.Controllers;

import com.RentBikeSystem.BikeService.DTO.APIDTO;
import com.RentBikeSystem.BikeService.DTO.BikeDTO;
import com.RentBikeSystem.BikeService.Services.BikeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("api/bike")
@AllArgsConstructor
@Slf4j
public class BikeController {
    private BikeService bikeService;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getBike(@PathVariable long id){
        return bikeService.getBike(id) != null
                ? new ResponseEntity<>(bikeService.getBike(id), HttpStatus.OK)
                : new ResponseEntity<>("Id not Exist",HttpStatus.NOT_FOUND);
    }




    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getAllBike(
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String location
    ) {

        if (model == null && location == null) {
            return new ResponseEntity<>("Please provide at least one filter (model or location).", HttpStatus.BAD_REQUEST);
        }

        if (model != null && location != null) {
            return new ResponseEntity<>(bikeService.getAllBikeByModelAndLocation(model, location), HttpStatus.OK);
        } else if (model != null) {
            return new ResponseEntity<>(bikeService.getAllBikeModel(model), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(bikeService.getAllBikeByLocation(location), HttpStatus.OK);
        }

    }

    @PostMapping("/")
    public ResponseEntity<?> addBike(@Valid @RequestBody BikeDTO bikeDTO){

        log.info("saveee here!!!!!");
       // bikeDTO.setId(59);
        try {
            APIDTO apidto = new APIDTO(bikeService.SaveBike(bikeDTO), "Bike is Created successfully");
            return new ResponseEntity<>(apidto, HttpStatus.CREATED);
        }catch (Exception e) {
            log.error("Error fetching bike addBike  " , e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBike(@PathVariable long id,@Valid@RequestBody BikeDTO bikeDTO){
        APIDTO apidto = new APIDTO(bikeService.UpdateBike(id,bikeDTO), "Bike updated successfully");
        return new ResponseEntity<>(apidto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBikePatch(@PathVariable long id, @RequestBody Map<String, Object> attributeUpdate){
        APIDTO apidto = new APIDTO(bikeService.UpdateBikePart(id, attributeUpdate), "Bike updated successfully");
        return new ResponseEntity<>(apidto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        if (bikeService.Delete(id)) {
            return new ResponseEntity<>("Deleted Done successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Bike not found or deletion failed", HttpStatus.NOT_FOUND);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteAll(@RequestBody List<Long> Ids){

        APIDTO apidto=bikeService.DeleteBikes(Ids);
        return  new ResponseEntity<>(apidto,apidto.getHttpStatus());

    }

}
