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

import java.util.ArrayList;
import java.util.List;
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
        List<Unavilability> unavilabilities= unavilabilityRepository.findAllByAccomodationId(unavilabilityDTO.getAccomodationId());
        unavilabilityDTO.convertDate();
        if(accomodation.isPresent()){

            for(DateRange existing_unavilability : unavilabilities){
                if (!chackDaysRange(unavilabilityDTO, existing_unavilability)){
                    throw new BadRequestException("You alreday chose that your accomodation is unavilable in that time.");
                }
            }

            for(DateRange existing_reservations : reservationRepository.findByAccomodation(accomodation.get().getId())){
                if (chackDaysRange(unavilabilityDTO, existing_reservations)){
                    throw new BadRequestException("You alreday have reservation for that period");
                }
            }

            Unavilability unavilability = new Unavilability(unavilabilityDTO);
            return new UnavilabilityDTO(unavilabilityRepository.save(unavilability));
        }
        throw  new BadRequestException("There is no accomodation with given id");
    }

    public boolean chackDaysRange(UnavilabilityDTO unavilabilityDTO, DateRange  existingDateRange){
        return (unavilabilityDTO.getDateFrom().after(existingDateRange.getDateFrom()) &&
                unavilabilityDTO.getDateFrom().after(existingDateRange.getDateTo())) ||
                (unavilabilityDTO.getDateTo().before(existingDateRange.getDateFrom()) &&
                        unavilabilityDTO.getDateFrom().before(existingDateRange.getDateFrom()));
    }

    public List<UnavilabilityDTO> getListUnavilabilitiesForAccomodation(String accomodationId) {
        List<Unavilability> unavilabilities= unavilabilityRepository.findByAccomodationId(accomodationId);
        List<UnavilabilityDTO> unavilabilityDTOS = new ArrayList<>();
        for(Unavilability u : unavilabilities){
            unavilabilityDTOS.add(new UnavilabilityDTO(u));
        }
        return unavilabilityDTOS;
    }
}
