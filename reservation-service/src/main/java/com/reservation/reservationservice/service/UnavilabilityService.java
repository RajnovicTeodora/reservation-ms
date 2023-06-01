package com.reservation.reservationservice.service;

import com.reservation.reservationservice.dtos.UnavilabilityDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.model.Accomodation;
import com.reservation.reservationservice.model.DateRange;
import com.reservation.reservationservice.model.Unavilability;
import com.reservation.reservationservice.repository.AccomodationRepository;
import com.reservation.reservationservice.repository.ReservationRepository;
import com.reservation.reservationservice.repository.UnavilabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnavilabilityService {

    @Autowired
    private UnavilabilityRepository unavilabilityRepository;
    @Autowired
    private AccomodationRepository accomodationRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    public UnavilabilityDTO save(UnavilabilityDTO unavilabilityDTO) throws Exception {

        Optional<Accomodation> accomodation = accomodationRepository.findById(unavilabilityDTO.getAccomodationId());

        if(accomodation.isPresent()){
            for(DateRange existing_unavilability : accomodation.get().getUnavilabilities()){
                if (chackDaysRange(unavilabilityDTO, existing_unavilability)){
                    throw new BadRequestException("You alreday chose that your accomodation is unavilable in that time.");
                }
            }
            for(DateRange existing_reservations : reservationRepository.findByAccomodationId(accomodation.get().getId())){
                if (chackDaysRange(unavilabilityDTO, existing_reservations)){
                    throw new BadRequestException("You alreday have reservation for that period");
                }
            }
        }
        Unavilability unavilability = new Unavilability(unavilabilityDTO);
        return new UnavilabilityDTO(unavilabilityRepository.save(unavilability));

    }

    public boolean chackDaysRange(UnavilabilityDTO unavilabilityDTO, DateRange  existingDateRange){
        return (unavilabilityDTO.getDateFrom().after(existingDateRange.getDateFrom()) &&
                unavilabilityDTO.getDateFrom().after(existingDateRange.getDateTo())) ||
                (unavilabilityDTO.getDateTo().before(existingDateRange.getDateFrom()) &&
                        unavilabilityDTO.getDateTo().after(existingDateRange.getDateTo()));
    }
}
