package com.RentBikeSystem.RentalRecordService.Services.Imp;


import com.RentBikeSystem.RentalRecordService.DTO.JourneyDto;
import com.RentBikeSystem.RentalRecordService.Entity.Journey;
import com.RentBikeSystem.RentalRecordService.Entity.PointOfRental;
import com.RentBikeSystem.RentalRecordService.Entity.StateJounery;
import com.RentBikeSystem.RentalRecordService.Kafka.BikeProducer;
import com.RentBikeSystem.RentalRecordService.Repository.JouneryRepository;
import com.RentBikeSystem.RentalRecordService.Services.JouneryServices;
import com.RentBikeSystem.RentalRecordService.Services.PointOfRentalServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class JouneryServicesImp implements JouneryServices {

    @Autowired
    private JouneryRepository jouneryRepository;
    @Autowired
    private PointOfRentalServices pointOfRentalServices;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    BikeProducer bikeProducer;


    /**
     * @param id
     * @return
     */
    @Override
    public boolean IsExistJounery(Long id) {
        return  jouneryRepository.existsById(id);
    }
    /**
     * @param journeyDto
     * @return journey
     */
    @Override
    public JourneyDto createJourney(JourneyDto journeyDto) {
        journeyDto.setId(null);
        Journey journey=modelMapper.map(journeyDto,Journey.class);
        journey.setStateJounery(StateJounery.WaitingPayment);
        Journey  saved=jouneryRepository.save(journey);
        return modelMapper.map(saved,JourneyDto.class) ;
    }

    /**
     * @param journeyId
     * @return
     */
    @Override
    public boolean startJourney(Long journeyId) {
       Optional< Journey> optionalJourney=jouneryRepository.findById(journeyId);
        Journey journey =optionalJourney.get();
        LocalDateTime time= LocalDateTime.now();
        journey.setStartJounery(time);
        journey.setStateJounery(StateJounery.Running);
        Journey Updated=jouneryRepository.save(journey);
        boolean flag=Updated.getStartJounery().equals(time);
        if(flag)
        {
            Map<String,Object> objectMap=new HashMap<>();
            objectMap.put("idBike",journey.getIdBike());
            objectMap.put("idPointOfRental",journey.getStartPointOfRental());
            objectMap.put("StateBike","RENTED");
            bikeProducer.sendMessageBikeAvailability(objectMap);
        }
         return flag;
    }

    /**
     * @param journeyId
     * @param endPointId
     * @return
     */
    @Override
    public boolean completeJourney(Long journeyId, Long endPointId) {
        return ChangeStateJourney( journeyId, endPointId ,StateJounery.Done);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public boolean IsCompletedJourney(long userId) {
        List<Journey>journeys= jouneryRepository.findAllJourneyByIdUser(StateJounery.Running,userId);
        return journeys.isEmpty();
    }

    /**
     * @param idbike
     * @return
     */
    @Override
    public boolean IsBikeNotUsed(long idbike) {

        List<Journey>journeys= jouneryRepository.findAllUsersByIdBike(StateJounery.Running,idbike);
        return journeys.isEmpty();
    }

    /**
     * @param journeyId
     * @return
     */
    @Override
    public boolean cancelJourney(Long journeyId,Long endPointId) {
       return ChangeStateJourney( journeyId, endPointId ,StateJounery.Cancel);
    }
    private boolean  ChangeStateJourney(Long journeyId,Long endPointId ,StateJounery stateJounery){
        Optional< Journey> optionalJourney=jouneryRepository.findById(journeyId);
        Journey journey=optionalJourney.get();

        journey.setEndPointOfRental(modelMapper.map(pointOfRentalServices.getPointOfRentalById(endPointId), PointOfRental.class));
        journey.setStateJounery(stateJounery);
        journey.setEndJounery(LocalDateTime.now());


        Map<String,Object> objectMap=new HashMap<>();
        objectMap.put("idBike",journey.getIdBike());
        objectMap.put("idPointOfRental",endPointId);
        objectMap.put("StateBike","AVAILABLE");
        bikeProducer.sendMessageBikeAvailability(objectMap);
        return true;
    }

    /**
     * @param journeyId
     * @return
     */
    @Override
    public JourneyDto getJourneyById(Long journeyId) {
        Optional<Journey>  optionalJourney=jouneryRepository.findById(journeyId);
        return modelMapper.map(optionalJourney.orElse(null),JourneyDto.class) ;
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<JourneyDto> getJourneysByUser(Long userId) {
      return   jouneryRepository.findAllByIdUser(userId).stream()
                .map( journey ->  modelMapper.map(journey,JourneyDto.class)).toList();
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public JourneyDto getJourneyByUser(Long userId) {
        List<Journey> journeys=  jouneryRepository.findAllJourneyByIdUser(StateJounery.Running,userId);
       if(!journeys.isEmpty())
           return modelMapper.map(journeys.get(0),JourneyDto.class);

       return null;
    }

    /**
     * @param bikeId
     * @return
     */
    @Override
    public List<JourneyDto> getJourneysByBike(Long bikeId) {
        return   jouneryRepository.findAllByIdBike(bikeId).stream()
                .map( journey ->  modelMapper.map(journey,JourneyDto.class)).toList();
    }

    /**
     * @param bikeId
     * @return
     */
    @Override
    public List<Long> getUsersByBike(Long bikeId) {

        return jouneryRepository.findAllUsersByBike(bikeId);
    }

    /**
     * @return
     */
    @Override
    public List<JourneyDto> getActiveJourneys() {
        return   jouneryRepository.findAllByStateJounery(StateJounery.Running).stream()
                .map( journey ->  modelMapper.map(journey,JourneyDto.class)).toList();
    }

    /**
     * @param hours
     * @return
     */
    @Override
    public List<JourneyDto> getActiveJourneys(float hours , Pageable pageable) {

        Page<Object[]> results= jouneryRepository.getActiveJourneys(StateJounery.Running,hours,pageable);
      return   results.stream().map(result ->{
                    Journey journey=(Journey)result[0];
                    journey.setTotalHours((float) result[0]);
                    return  modelMapper.map(journey,JourneyDto.class);
                }
                ).toList();

    }

    /**
     * @param hours
     * @return
     */
    @Override
    public List<JourneyDto> getActiveJourneys(float hours) {
        List<Object[]> results= jouneryRepository.getActiveJourneys(StateJounery.Running,hours);
        return   results.stream().map(result ->{
                    Journey journey=(Journey)result[0];
                    journey.setTotalHours((float) result[0]);
                    return  modelMapper.map(journey,JourneyDto.class);
                }
        ).toList();
    }
}
