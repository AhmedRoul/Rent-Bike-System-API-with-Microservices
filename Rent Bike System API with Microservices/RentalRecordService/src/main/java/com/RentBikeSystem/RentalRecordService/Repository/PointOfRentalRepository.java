package com.RentBikeSystem.RentalRecordService.Repository;

import com.RentBikeSystem.RentalRecordService.Entity.PointOfRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PointOfRentalRepository extends JpaRepository<PointOfRental,Long> {

    List<PointOfRental> findAllByCityIgnoreCase(String city);
    List<PointOfRental> findAllByCityContainingIgnoreCase(String city);

    List<PointOfRental> findAllByAddressContainingIgnoreCase(String address);
    List<PointOfRental> findAllByNameAreaContainingIgnoreCase(String nameArea);

    List<PointOfRental> findAllByCityContainingIgnoreCaseOrAddressContainingIgnoreCase(String city, String address);

    @Query("SELECT p FROM PointOfRental p " +
            "LEFT JOIN p.startJourneys sj " +
            "LEFT JOIN p.endJourneys ej " +
            "GROUP BY p " +
            "ORDER BY COUNT(sj) + COUNT(ej) DESC")
    List<PointOfRental> findMostPopularPointOfRental();

    @Query("SELECT p FROM PointOfRental p " +
            "LEFT JOIN p.startJourneys sj " +
            "LEFT JOIN p.endJourneys ej " +
            "WHERE p.city = :city " +
            "GROUP BY p " +
            "ORDER BY COUNT(sj) + COUNT(ej) DESC")
    List<PointOfRental> findMostPopularPointOfRental(@Param("city") String city);

    @Query(value = "select p from point_of_rental p " +
            " where calculatedistance(:lat, :lon, p.latitude, p.longitude) <= :radius "
            , nativeQuery = true)
    List<PointOfRental> findNearLocation
            (@Param("lat") BigDecimal latitude,@Param("lon") BigDecimal longitude, @Param("radius")float radiusInKm);
}
