package com.RentBikeSystem.BikeService.Services;

import com.RentBikeSystem.BikeService.DTO.APIDTO;
import com.RentBikeSystem.BikeService.DTO.BikeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface BikeService {
    BikeDTO SaveBike(BikeDTO bikeDTO);
    BikeDTO getBike(long id);

    boolean ISRent(long idbike);

    List<BikeDTO> getAllBikeModel(String Model);

    List<BikeDTO> getAllBikeByModelAndLocation(String Location, String Model);
    List<BikeDTO> getAllBikeByLocation(String Location);

    BikeDTO UpdateBike(long id,BikeDTO bikeDTO);

    BikeDTO UpdateBikePart(long id , Map<String, Object> attributeUpdate);

    boolean Delete(long id);
    APIDTO DeleteBikes(List<Long> ids);



}
