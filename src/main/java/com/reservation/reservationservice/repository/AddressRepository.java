package com.reservation.reservationservice.repository;

import com.reservation.reservationservice.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository  extends MongoRepository<Address, String> {
}
