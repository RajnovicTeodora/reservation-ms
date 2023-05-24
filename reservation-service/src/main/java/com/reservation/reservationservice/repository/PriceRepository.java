package com.reservation.reservationservice.repository;

import com.reservation.reservationservice.model.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository  extends MongoRepository<Price, String> {
}
