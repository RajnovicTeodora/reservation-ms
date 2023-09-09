package com.reservation.reservationservice.repository;

import com.reservation.reservationservice.model.Guest;
import com.reservation.reservationservice.model.Host;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository  extends MongoRepository<Guest, String> {
    Optional<Guest> findByUsername(String username);
    Optional<Guest> findGuestByUsername(String username);
}
