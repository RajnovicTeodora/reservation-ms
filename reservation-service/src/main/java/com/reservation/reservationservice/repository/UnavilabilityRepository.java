package com.reservation.reservationservice.repository;

import com.reservation.reservationservice.model.Unavilability;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnavilabilityRepository  extends MongoRepository<Unavilability, String> {
}
