package com.reservation.reservationservice.repository;

import com.reservation.reservationservice.model.Request;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RequestRepository  extends MongoRepository<Request, String> {
    Collection<Request> findAllByGuestId(String userId);
}
