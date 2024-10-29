package com.RentBikeSystem.RentalRecordService.Services.Imp;

import com.RentBikeSystem.RentalRecordService.DTO.PointOfRentalDto;
import com.RentBikeSystem.RentalRecordService.Entity.PointOfRental;
import com.RentBikeSystem.RentalRecordService.Repository.PointOfRentalRepository;
import com.RentBikeSystem.RentalRecordService.Services.PointOfRentalServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
public class PointOfRentalServicesImp implements PointOfRentalServices {
    @Autowired
    private  PointOfRentalRepository pointOfRentalRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * @return
     */
    @Override
    public List<PointOfRentalDto> getMostPopularPointOfRental() {
        List<PointOfRental> list=pointOfRentalRepository.findMostPopularPointOfRental();
        return list.stream().map( ele ->modelMapper.map(ele ,PointOfRentalDto.class)).toList();
    }

    /**
     * @param City
     * @return
     */
    @Override
    public List<PointOfRentalDto> getMostPopularPointOfRental(String City) {
        List<PointOfRental> list=pointOfRentalRepository.findMostPopularPointOfRental(City);
        return list.stream().map( ele ->modelMapper.map(ele ,PointOfRentalDto.class)).toList();
    }

    /**
     * @param pointOfRentalId
     * @return
     */
    @Override
    public boolean existPointOfRental(long pointOfRentalId) {
        return  pointOfRentalRepository.findById(pointOfRentalId).isPresent();

    }
/*

    */
/**
     * @param pointOfRentalDto
     * @return
     */

    @Override
    public PointOfRentalDto createPointOfRental(PointOfRentalDto pointOfRentalDto) {
        pointOfRentalDto.setId(null);
        PointOfRental  pointOfRental=modelMapper.map(pointOfRentalDto ,PointOfRental.class);
        PointOfRental saved =pointOfRentalRepository.save(pointOfRental);
        if(saved.getId()!=null)
            return modelMapper.map(saved ,PointOfRentalDto.class);

        return null;
    }

/**
     * @param pointOfRentalId
     * @param pointOfRentalDetails
     * @return
     */

    @Override
    public PointOfRentalDto updatePointOfRental(long pointOfRentalId, PointOfRentalDto pointOfRentalDetails) {
        PointOfRental savedpointOfRental= modelMapper.map(getPointOfRentalById(pointOfRentalId),PointOfRental.class);
        PointOfRental  pointOfRental=modelMapper.map(pointOfRentalDetails ,PointOfRental.class);
        pointOfRental.setId(savedpointOfRental.getId());
        pointOfRental.setEndJourneys(savedpointOfRental.getEndJourneys());
        pointOfRental.setStartJourneys(savedpointOfRental.getStartJourneys());

        PointOfRental updated= pointOfRentalRepository.save(pointOfRental);
        if(updated.getId()!=null)
            return modelMapper.map(updated ,PointOfRentalDto.class);

        return null;
    }

/**
     * @param pointOfRentalId
     * @return
     */

    @Override
    public PointOfRentalDto getPointOfRentalById(long pointOfRentalId) {
        Optional<PointOfRental>pointOfRental= pointOfRentalRepository.findById(pointOfRentalId);
        return pointOfRental.map(ofRental -> modelMapper.map(ofRental, PointOfRentalDto.class)).orElse(null);
    }

/**
     * @return
     */

    @Override
    public List<PointOfRentalDto> getAllPointsOfRental() {
        List<PointOfRental> list=pointOfRentalRepository.findAll();

        return list.stream().map( ele ->modelMapper.map(ele ,PointOfRentalDto.class)).toList();
    }

/**
     * @param pointOfRentalId
     */

    @Override
    public boolean deletePointOfRental(long pointOfRentalId) {
        if(getPointOfRentalById(pointOfRentalId)!=null) {
            pointOfRentalRepository.deleteById(pointOfRentalId);
            return true;
        }
        return false;
    }

/**
     * @param city
     * @return
     */

    @Override
    public List<PointOfRentalDto> getPointsOfRentalByCity(String city) {
        List<PointOfRental> list=pointOfRentalRepository.findAllByCityIgnoreCase(city);
        return list.stream().map( ele ->modelMapper.map(ele ,PointOfRentalDto.class)).toList();
    }

/**
     * @param city
     * @return
     */

    @Override
    public List<PointOfRentalDto> getPointsOfRentalSearchByCity(String city) {
        List<PointOfRental> list=pointOfRentalRepository.findAllByCityContainingIgnoreCase(city);
        return list.stream().map( ele ->modelMapper.map(ele ,PointOfRentalDto.class)).toList();
    }

/**
     * @param Address
     * @return
     */

    @Override
    public List<PointOfRentalDto> getPointsOfRentalSearchByAddress(String Address) {
        List<PointOfRental> list=pointOfRentalRepository.findAllByAddressContainingIgnoreCase(Address);
        return list.stream().map( ele ->modelMapper.map(ele ,PointOfRentalDto.class)).toList();
    }

/**
     * @param NameArea
     * @return
     */

    @Override
    public List<PointOfRentalDto> getPointsOfRentalSearchByNameArea(String NameArea) {
        List<PointOfRental> list=pointOfRentalRepository.findAllByNameAreaContainingIgnoreCase(NameArea);
        return list.stream().map( ele ->modelMapper.map(ele ,PointOfRentalDto.class)).toList();
    }

/**
     * @param city
     * @param Address
     * @return
     */

    @Override
    public List<PointOfRentalDto> getPointsOfRentalSearchByAddressAndCity(String city, String Address) {
        List<PointOfRental> list=
                pointOfRentalRepository.findAllByCityContainingIgnoreCaseOrAddressContainingIgnoreCase(city,Address);
        return list.stream().map( ele ->modelMapper.map(ele ,PointOfRentalDto.class)).toList();
    }

/**
     * @param latitude
     * @param longitude
     * @param radiusInKm
     * @return
     */

    @Override
    public List<PointOfRentalDto> getPointsOfRentalNearLocation(BigDecimal latitude, BigDecimal longitude, float radiusInKm) {
        List<PointOfRental> list=
                pointOfRentalRepository.findNearLocation(latitude,longitude ,radiusInKm);
        return list.stream().map( ele ->modelMapper.map(ele ,PointOfRentalDto.class)).toList();

    }

}
