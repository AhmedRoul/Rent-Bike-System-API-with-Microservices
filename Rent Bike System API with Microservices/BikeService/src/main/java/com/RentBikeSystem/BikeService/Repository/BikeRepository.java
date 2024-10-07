package com.RentBikeSystem.BikeService.Repository;

import com.RentBikeSystem.BikeService.Model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface BikeRepository extends JpaRepository<Bike,Long> {


    List<Bike> findAllByModel(String Model);
    List<Bike> findAllByModelAndLocation(String Model ,String Location);
    List<Bike> findAllByLocation(String Location);
    void  deleteByIdIn(List<Long> ids);

}
