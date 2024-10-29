package com.RentBikeSystem.BikeService.Kafka;

import com.RentBikeSystem.BikeService.DTO.BikeDTO;
import com.RentBikeSystem.BikeService.Model.AvailabilityStatusBike;
import com.RentBikeSystem.BikeService.Services.BikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class BikeConsumer {

    @Autowired
    private BikeService bikeService;


    @KafkaListener(topics = "bike.availability.status"
            ,groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessageBikeAvailability(Map<String ,Object> objectMap){

        log.info(String.format("WE receiver message  in bike server is  %s" ,objectMap.toString()));

       BikeDTO bikeDTO= bikeService.getBike((Long)objectMap.get("idBike"));
       if(objectMap.get("StateBike")=="AVAILABLE")
       {
           bikeDTO.setAvailabilityStatusBike(AvailabilityStatusBike.AVAILABLE);
       }
       else if(objectMap.get("StateBike")=="RENTED"){
           bikeDTO.setAvailabilityStatusBike(AvailabilityStatusBike.RENTED);
       }

       bikeDTO.setIdPointOfRental((Long)objectMap.get("idBike"));

        bikeService.UpdateBike(bikeDTO.getId(),bikeDTO);

    }

}
