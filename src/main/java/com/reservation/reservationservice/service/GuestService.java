package com.reservation.reservationservice.service;

import com.reservation.reservationservice.model.Guest;
import com.reservation.reservationservice.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;


    public void save(String username) {
        Guest guest = new Guest();
        guest.setUsername(username);
        guestRepository.save(guest);
    }
}
