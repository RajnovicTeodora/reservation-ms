package com.reservation.reservationservice.repository;

import com.reservation.reservationservice.model.RegistratedUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistratedUserRepository  extends MongoRepository<RegistratedUser, String> {
}
