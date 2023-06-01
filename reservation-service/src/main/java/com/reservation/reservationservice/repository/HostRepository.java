package com.reservation.reservationservice.repository;


import com.reservation.reservationservice.model.Host;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository  extends MongoRepository<Host, String> {
}
