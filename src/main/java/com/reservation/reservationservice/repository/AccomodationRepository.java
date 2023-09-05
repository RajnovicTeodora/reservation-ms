package com.reservation.reservationservice.repository;

import com.reservation.reservationservice.model.Accomodation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface AccomodationRepository extends MongoRepository<Accomodation, String> {
    Optional<Accomodation> findAccomodationByName(String name);

    Optional<Accomodation> findAccomodationById(String accommodationId);
}