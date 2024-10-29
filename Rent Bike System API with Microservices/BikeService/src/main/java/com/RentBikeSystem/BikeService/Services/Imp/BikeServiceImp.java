package com.RentBikeSystem.BikeService.Services.Imp;

import com.RentBikeSystem.BikeService.DTO.APIDTO;
import com.RentBikeSystem.BikeService.DTO.BikeDTO;
import com.RentBikeSystem.BikeService.Model.AvailabilityStatusBike;
import com.RentBikeSystem.BikeService.Model.Bike;
import com.RentBikeSystem.BikeService.Repository.BikeRepository;
import com.RentBikeSystem.BikeService.Services.BikeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class BikeServiceImp implements BikeService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BikeRepository bikeRepository;


    @Override
    @CachePut(value = "bikes", key = "#result.id")
    public BikeDTO SaveBike(BikeDTO bikeDTO) {
        Bike bike =modelMapper.map(bikeDTO,Bike.class);
        Bike saved=  bikeRepository.save(bike);
        return modelMapper.map(saved,BikeDTO.class);
    }

    @Override
    @Cacheable(value = "bikes", key = "#id")
    public BikeDTO getBike(long id)
    {
        Optional<Bike> bikeOptional =bikeRepository.findById(id);
        if(bikeOptional.isPresent())
        {
            return modelMapper.map(bikeOptional.get(),BikeDTO.class);
        }
        return null;
    }

    @Override
    @Cacheable(value = "bikes", key = "#id")
    public boolean ISRent(long idbike) {
        Optional<Bike> bikeOptional= bikeRepository.findById(idbike);
        if(bikeOptional.isPresent()){
            return bikeOptional.get().getAvailabilityStatusBike().equals(AvailabilityStatusBike.RENTED);
        }
        return false;
    }

    @Override
    @Cacheable(value = "bikes", key = "#Model")
    public List<BikeDTO> getAllBikeModel(String Model) {
        List<Bike> bikes  =bikeRepository.findAllByModel(Model);
        List<BikeDTO> bikeDTOS=bikes.stream()
                .map(bike -> modelMapper.map(bike, BikeDTO.class))
                .toList();

        return bikeDTOS;
    }

    @Override
    @Cacheable(value = "bikes", key = "{#Location, #Model}")
    public List<BikeDTO> getAllBikeByModelAndLocation(String Location, String Model) {
        List<Bike> bikes  =bikeRepository.findAllByModelAndLocation(Model,Location);
        List<BikeDTO> bikeDTOS=bikes.stream()
                .map(bike -> modelMapper.map(bike, BikeDTO.class))
                .toList();

        return bikeDTOS;
    }

    @Override
    @Cacheable(value = "bikes", key = "#Location")
    public List<BikeDTO> getAllBikeByLocation(String Location) {
        List<Bike> bikes  =bikeRepository.findAllByLocation(Location);
        List<BikeDTO> bikeDTOS=bikes.stream()
                .map(bike -> modelMapper.map(bike, BikeDTO.class))
                .toList();
        return bikeDTOS;
    }


    @Override
    @CachePut(value = "bikes", key = "#id")
    public BikeDTO UpdateBike(long id,@Valid BikeDTO bikeDTO) {
        if(bikeDTO.getId()!=0&&id!=bikeDTO.getId()){
            return null;
        }
        Optional<Bike> bikeOptional =bikeRepository.findById(bikeDTO.getId());
        if(bikeOptional.isPresent())
        {
            Bike bike =modelMapper.map(bikeDTO,Bike.class);
            Bike Updated=  bikeRepository.save(bike);
            BikeDTO bikeDTOUpdated =modelMapper.map(Updated,BikeDTO.class);
            return bikeDTOUpdated;
        }
        return null;
    }


    @Override
    @CachePut(value = "bikes", key = "#id")
    public BikeDTO UpdateBikePart(long id, Map<String, Object> attributeUpdate) {
        Optional<Bike> bikeOptional = bikeRepository.findById(id);
        if (bikeOptional.isPresent()) {
            Bike bike = bikeOptional.get();

            attributeUpdate.forEach((key, value) -> {
                try {
                    Field field = Bike.class.getDeclaredField(key);
                    field.setAccessible(true);

                    Class<?> fieldType = field.getType();
                    if (fieldType.equals(float.class) && value instanceof Double) {
                        field.set(bike, ((Double) value).floatValue());
                    } else if (fieldType.equals(double.class) && value instanceof Float) {
                        field.set(bike, ((Float) value).doubleValue());
                    } else {
                        field.set(bike, value);
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException("Error updating bike field: " + key, e);
                }
            });
            return UpdateBike(id,modelMapper.map(bike, BikeDTO.class));

        }
        return null;
    }


    @Override
    @CacheEvict(value = "bikes", key = "#id")
    public boolean Delete(long id) {
        Optional<Bike> bikeOptional =bikeRepository.findById(id);
        if(bikeOptional.isPresent()) {
            bikeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @CacheEvict(value = "bikes", allEntries = true)
    public APIDTO DeleteBikes(List<Long> ids) {
        List<Bike> bikes=bikeRepository.findAllById(ids);
        if(bikes.size()==ids.size())//Ids all exist
        {
            bikeRepository.deleteAllInBatch(bikes);
            return  APIDTO.builder().httpStatus(HttpStatus.OK)
                    .Message("All IDs have been deleted successfully.").build();
        }
        Set<Long> existingBikeIds = bikes.stream()
                .map(Bike::getId)
                .collect(Collectors.toSet());

        List<Long> existingIds = ids.stream()
                .filter(existingBikeIds::contains)
                .toList();

        List<Long> missingIds = ids.stream()
                .filter(id -> !existingBikeIds.contains(id))
                .toList();
        bikeRepository.deleteByIdIn(existingIds);

        return  APIDTO.builder().Message("Some IDs were missing and not deleted. The existing IDs have been deleted. ")
                .httpStatus(HttpStatus.PARTIAL_CONTENT)
                .object(missingIds).build();
    }
}
