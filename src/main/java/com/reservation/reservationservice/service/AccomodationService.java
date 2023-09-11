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

import java.util.ArrayList;
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
        Optional<Host> host = hostRepository.findByUsername(accomodation.getUsername());
        if (!host.isPresent()){
            Host hostObj = new Host();
            hostObj.setUsername(accomodation.getUsername());
            hostObj.setAccomodations(new ArrayList<>());
            hostObj.getAccomodations().add(saved);
            hostObj.setUsername(accomodation.getUsername());
            hostRepository.save(hostObj);
            saved.setHost(new Host(hostObj.getId(), hostObj.getUsername(), new ArrayList<>()));
            accomodationRepository.save(saved);
        }else{
            if(host.get().getAccomodations()==null){ host.get().setAccomodations(new ArrayList<>());}
            host.get().getAccomodations().add(saved);
            hostRepository.save(host.get());
            saved.setHost(new Host(host.get().getId(), host.get().getUsername(), new ArrayList<>()));
            accomodationRepository.save(saved);

        }
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
}
