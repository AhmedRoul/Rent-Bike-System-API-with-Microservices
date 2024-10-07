package com.RentBikeSystem.BikeService.Model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "bikes")
public class Bike {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bike_id")
    private  long id;

    @Column(nullable = false,name  ="model")

    private String model;

    @Column(nullable = false,name  ="type_bike")
    @Enumerated(EnumType.ORDINAL)
    private  TypeBike typeBike;

    @Column(nullable = false,name  ="availability_status_bike")
    @Enumerated(EnumType.ORDINAL)
    private AvailabilityStatusBike availabilityStatusBike;
    @Column(nullable = false,name="rental_price")
    private  float rentalPrice;
    @Column(nullable = false,name="color")
    private  String color;
    @Column(nullable = false,name ="frame_size_bike" )
    @Enumerated(EnumType.ORDINAL)
    private  FrameSizeBike frameSizeBike;

    @Column(name="location")
    private  String location;
 @Column(name="distance_traveled")
    private float distanceTraveled=0.0f;//Total distance the bike has traveled in KM.
 @Column(name="last_maintenancedate")
    private LocalDateTime lastMaintenanceDate;

}
