package com.RentBikeSystem.BikeService.DTO;

import com.RentBikeSystem.BikeService.Model.AvailabilityStatusBike;
import com.RentBikeSystem.BikeService.Model.FrameSizeBike;
import com.RentBikeSystem.BikeService.Model.TypeBike;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BikeDTO {
    private  long id;
    private  Long idPointOfRental;

    @Size(max = 100,min = 6 ,message = "Model name must be between 6 and 100 characters.")
    private String model;

    @NotNull(message = "Bike type is required.")

    private TypeBike typeBike;
    @NotNull(message = "Availability status is required.")
    private AvailabilityStatusBike availabilityStatusBike;
    @DecimalMin(value = "5.0", message = "Rental price must be a positive number.")
    private  float rentalPrice;
    @NotBlank(message = "Color is required.")
    private  String color;
    @NotNull(message = "Frame size is required.")
    private FrameSizeBike frameSizeBike;
    @NotNull(message = "location  is required.")
    private  String location;
    private float distanceTraveled;//Total distance the bike has traveled in KM.
    private LocalDateTime lastMaintenanceDate;
}
