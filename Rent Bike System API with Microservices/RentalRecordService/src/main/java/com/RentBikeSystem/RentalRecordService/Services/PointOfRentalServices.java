package com.RentBikeSystem.RentalRecordService.Services;

import com.RentBikeSystem.RentalRecordService.DTO.PointOfRentalDto;
import com.RentBikeSystem.RentalRecordService.Entity.PointOfRental;

import java.math.BigDecimal;
import java.util.List;

public interface PointOfRentalServices {

    List<PointOfRentalDto> getMostPopularPointOfRental();
    List<PointOfRentalDto> getMostPopularPointOfRental(String City);
    boolean existPointOfRental(long pointOfRentalId);
    // Create a new point of rental
    PointOfRentalDto createPointOfRental(PointOfRentalDto pointOfRentalDto);

    // Update an existing point of rental
    PointOfRentalDto updatePointOfRental(long pointOfRentalId, PointOfRentalDto pointOfRentalDetails);

    // Get a specific point of rental by ID
    PointOfRentalDto getPointOfRentalById(long pointOfRentalId);

    // Get a list of all points of rental
    List<PointOfRentalDto> getAllPointsOfRental();

    // Delete a point of rental
    boolean deletePointOfRental(long pointOfRentalId);

    // Get points of rental by city
    List<PointOfRentalDto> getPointsOfRentalByCity(String city);
    List<PointOfRentalDto> getPointsOfRentalSearchByCity(String city);
    List<PointOfRentalDto> getPointsOfRentalSearchByAddress(String Address);
    List<PointOfRentalDto> getPointsOfRentalSearchByNameArea(String NameArea);
    List<PointOfRentalDto> getPointsOfRentalSearchByAddressAndCity(String city,String Address);


    // Get points of rental near a specific location (latitude, longitude)
    List<PointOfRentalDto> getPointsOfRentalNearLocation(BigDecimal latitude, BigDecimal longitude, float radiusInKm);
}
