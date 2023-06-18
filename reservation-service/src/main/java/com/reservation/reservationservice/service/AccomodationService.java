package com.reservation.reservationservice.service;
import com.reservation.reservationservice.dtos.AccomodationDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.model.Accomodation;
import com.reservation.reservationservice.repository.AccomodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccomodationService {

    @Autowired
    private AccomodationRepository accomodationRepository;

    public AccomodationDTO save(AccomodationDTO accomodation) throws Exception {
        if(accomodationRepository.findAccomodationByName(accomodation.getName()).isPresent()){
          throw new BadRequestException("Accomodation with name "+accomodation.getName()+" already exists.");
        }

        Accomodation newAccomodation = new Accomodation(accomodation);
        Accomodation saved = accomodationRepository.save(newAccomodation);
        return new AccomodationDTO(saved);
    }
}
