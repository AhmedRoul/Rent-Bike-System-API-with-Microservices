package com.RentBikeSystem.RentalRecordService.API.Client;


import com.RentBikeSystem.RentalRecordService.Services.JouneryServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BikeClientFallback{ /*implements BikeClient{
    @Autowired
    JouneryServices jouneryServices;
    *//**
     * @param id
     * @return
     *//*
    @Override
    public boolean IsRented(long id) {
        log.info("Inventory SERVICE IS DOWN  :Bike Server ");
        return !jouneryServices.IsBikeNotUsed(id);
    }

    *//**
     * @param id
     * @return
     *//*
    @Override
    public boolean IsBikeExist(Long id) {
        log.info("Inventory SERVICE IS DOWN  :Bike Server ");
        return false;
    }*/
}
