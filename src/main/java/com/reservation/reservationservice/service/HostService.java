package com.reservation.reservationservice.service;

import com.reservation.reservationservice.model.Guest;
import com.reservation.reservationservice.model.Host;
import com.reservation.reservationservice.repository.GuestRepository;
import com.reservation.reservationservice.repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class HostService {
    @Autowired
    private HostRepository hostRepository;


    public void save(String username) {
        Host host = new Host();
        host.setUsername(username);
        host.setAccomodations(new ArrayList<>());
        hostRepository.save(host);
    }
}
