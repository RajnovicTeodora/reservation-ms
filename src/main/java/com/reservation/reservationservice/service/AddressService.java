package com.reservation.reservationservice.service;

import com.reservation.reservationservice.model.Address;
import com.reservation.reservationservice.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository repo;
    public String save() {
        Address addres = new Address();
        addres.setCity("s");
        addres.setNumber("sd");
        addres.setStreet("fd");
        return repo.save(addres)+" ";
    }
}
