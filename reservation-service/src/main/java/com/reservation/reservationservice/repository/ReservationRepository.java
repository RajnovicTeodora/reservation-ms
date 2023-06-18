package com.reservation.reservationservice.repository;
import com.reservation.reservationservice.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository  extends MongoRepository<Reservation, String> {
    List<Reservation> findByAccomodation(String id);
}
