package com.RentBikeSystem.RentalRecordService.API.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "BikeService",url = "api/bike",fallback = BikeClientFallback.class)
public interface BikeClient {

    @GetMapping("/availability-rented/{id}")
    public boolean IsRented(@PathVariable long id);

    @GetMapping("/exist/id/{id}")
    public boolean IsBikeExist(@PathVariable Long id);
}
