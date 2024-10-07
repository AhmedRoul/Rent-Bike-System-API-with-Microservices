package com.RentBikeSystem.BikeService.Model;

public enum AvailabilityStatusBike {

    AVAILABLE         //The bike is available for rent.
    ,RENTED           //The bike is currently rented out by a user.
    ,RESERVED         //The bike is reserved by a user but not yet picked up.
    ,MAINTENANCE      //The bike is undergoing maintenance and is not available for rent.
    ,OUT_OF_SERVICE   //The bike is out of service and not available for use.
    ,LOST             //The bike is reported as lost and is no longer available in the system.
}
