package com.RentBikeSystem.RentalRecordService.Services;


import com.RentBikeSystem.RentalRecordService.DTO.JourneyDto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;

import java.util.List;

public interface JouneryServices {

    boolean IsExistJounery(Long id);

    // Create a new journey for a user and bike
    JourneyDto createJourney(JourneyDto journey);

    // Start the journey by updating the state
    boolean startJourney(Long journeyId);

    // Complete the journey at the end point
    boolean completeJourney(Long journeyId, Long endPointId);

    // get user complete his Journey ,
    // user shouldn't create new Journey when he don't complete his Journey
    boolean IsCompletedJourney(long userId);

    boolean IsBikeNotUsed(long idbike);

    // Cancel a journey
    boolean cancelJourney(Long journeyId,Long endPointId);

    // Fetch a specific journey by its ID
    JourneyDto getJourneyById(Long journeyId);

    // Fetch all journeys by a specific user
    List<JourneyDto> getJourneysByUser(Long userId);
    JourneyDto getJourneyByUser(Long userId);

    // Fetch all journeys for a specific bike
    List<JourneyDto> getJourneysByBike(Long bikeId);
    List<Long> getUsersByBike(Long bikeId);

    // Get all active journeys in progress
    List<JourneyDto> getActiveJourneys();
    // Get all active journeys in progress filter hour
    List<JourneyDto> getActiveJourneys(float hours, Pageable pageable);
    List<JourneyDto> getActiveJourneys(float hours);
}
