package com.reservation.reservationservice.repository;

import com.reservation.reservationservice.model.Price;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PriceRepository  extends MongoRepository<Price, String> {
    List<Price> findByAccomodationId(String accomodationId);

    Price findByAccomodationIdAndDateToIsNull(String accomodationId);
}