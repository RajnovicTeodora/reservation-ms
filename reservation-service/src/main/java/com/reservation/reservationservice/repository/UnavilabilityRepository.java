package com.reservation.reservationservice.repository;

import com.reservation.reservationservice.model.Price;
import com.reservation.reservationservice.model.Unavilability;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnavilabilityRepository  extends MongoRepository<Unavilability, String> {
    List<Unavilability> findAllByAccomodationId(String accomodationId);

    List<Unavilability> findByAccomodationId(String accomodationId);
}
