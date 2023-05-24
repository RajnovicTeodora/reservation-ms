package com.reservation.reservationservice.repository;

import com.reservation.reservationservice.model.Guest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository  extends MongoRepository<Guest, String> {
}
