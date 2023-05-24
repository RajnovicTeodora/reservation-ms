package com.reservation.reservationservice.repository;

import com.reservation.reservationservice.model.Accomodation;
import com.reservation.reservationservice.model.UnregistratedUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnregistradeUserRepository  extends MongoRepository<UnregistratedUser, String> {
}
