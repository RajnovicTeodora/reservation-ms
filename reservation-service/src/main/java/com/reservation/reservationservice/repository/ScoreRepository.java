package com.reservation.reservationservice.repository;

import com.reservation.reservationservice.model.Score;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository  extends MongoRepository<Score, String> {
}
