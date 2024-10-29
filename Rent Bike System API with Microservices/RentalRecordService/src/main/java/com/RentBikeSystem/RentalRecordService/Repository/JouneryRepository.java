package com.RentBikeSystem.RentalRecordService.Repository;

import com.RentBikeSystem.RentalRecordService.DTO.JourneyDto;
import com.RentBikeSystem.RentalRecordService.Entity.Journey;
import com.RentBikeSystem.RentalRecordService.Entity.StateJounery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JouneryRepository extends JpaRepository<Journey,Long> {
    boolean existsById(Long Id);


    @Query("SELECT j FROM Journey j WHERE j.idUser = :idUser AND j.stateJounery = :stateJounery")
    List<Journey> findAllJourneyByIdUser(@Param("stateJounery") StateJounery stateJounery, @Param("idUser") long idUser);

    @Query("SELECT j FROM Journey j WHERE j.idBike = :idBike AND j.stateJounery = :stateJounery")
    List<Journey> findAllUsersByIdBike(@Param("stateJounery") StateJounery stateJounery, @Param("idBike") long idBike);


    @Query("select j.idUser from Journey j where j.idBike = :idBike")
    List<Long> findAllUsersByBike(@Param("idBike") long idBike);

    List<Journey>  findAllByIdUser(Long idUser);

    List<Journey>  findAllByIdBike(Long idBike);

    List<Journey> findAllByStateJounery(StateJounery stateJounery);


    @Query(value = "SELECT j.*, TIMESTAMPDIFF(HOUR, j.start_journey, CURRENT_TIMESTAMP) AS hourDiff " +
            "FROM journey j WHERE j.state_journey = :stateJounery " +
            "AND TIMESTAMPDIFF(HOUR, j.start_journey, CURRENT_TIMESTAMP) > :hours",
            nativeQuery = true)
    Page<Object[]> getActiveJourneys(@Param("stateJounery")StateJounery stateJounery ,
                                     @Param("hours") float hours , Pageable pageable);

    @Query(value = "SELECT j.*, TIMESTAMPDIFF(HOUR, j.start_journey, CURRENT_TIMESTAMP) AS hourDiff " +
            "FROM journey j WHERE j.state_journey = :stateJounery " +
            "AND TIMESTAMPDIFF(HOUR, j.start_journey, CURRENT_TIMESTAMP) > :hours",
            nativeQuery = true)
    List<Object[]> getActiveJourneys(@Param("stateJounery")StateJounery stateJounery ,
                                     @Param("hours") float hours );

}
