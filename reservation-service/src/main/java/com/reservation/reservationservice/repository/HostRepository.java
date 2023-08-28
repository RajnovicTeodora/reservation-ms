package com.reservation.reservationservice.repository;


import com.reservation.reservationservice.model.Host;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HostRepository  extends MongoRepository<Host, String> {
    Optional<Host> findByUsername(String username);
}
