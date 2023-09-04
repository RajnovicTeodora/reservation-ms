package com.reservation.reservationservice.service;
import com.reservation.reservationservice.dtos.AccomodationDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.exceptions.NotFoundException;
import com.reservation.reservationservice.model.Accomodation;
import com.reservation.reservationservice.model.Host;
import com.reservation.reservationservice.repository.AccomodationRepository;
import com.reservation.reservationservice.repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AccomodationService {

    @Autowired
    private AccomodationRepository accomodationRepository;

    @Autowired
    private HostRepository hostRepository;

    public AccomodationDTO save(AccomodationDTO accomodation) throws Exception {
        if(accomodationRepository.findAccomodationByName(accomodation.getName()).isPresent()){
          throw new BadRequestException("Accomodation with name "+accomodation.getName()+" already exists.");
        }

        Accomodation newAccomodation = new Accomodation(accomodation);
        Accomodation saved = accomodationRepository.save(newAccomodation);
        return new AccomodationDTO(saved);
    }

    public void deleteAccommodations(String hostUsername){
        Optional<Host> g = hostRepository.findHostByUsername(hostUsername);
        if (!g.isPresent()) {
            throw new NotFoundException("Host not found");
        }
        for (Accomodation a : g.get().getAccomodations() ) {
            a.setDeleted(true);
        }
        accomodationRepository.saveAll(g.get().getAccomodations());
    }

    public String getHostUsernameByAccId(String accId, boolean isName){
        if(!isName) {
            Optional<Accomodation> accomodation = accomodationRepository.findById(accId);
            if (accomodation.isPresent()) {
                return accomodation.get().getHost().getUsername();
            } else {
                throw new NotFoundException("Accommodation not found");
            }
        }else{
            Optional<Accomodation> accomodation = accomodationRepository.findAccomodationByName(accId);
            if (accomodation.isPresent()) {
                return accomodation.get().getHost().getUsername();
            } else {
                throw new NotFoundException("Accommodation not found");
            }
        }
    }
}
