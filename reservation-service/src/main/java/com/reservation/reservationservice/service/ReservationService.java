package com.reservation.reservationservice.service;

import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.model.Reservation;
import com.reservation.reservationservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public boolean cancelReservation(String id) throws BadRequestException {

        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()){
            Date dateBefore = new Date();
            Date dateAfter = reservation.get().getDateFrom();

            long dateBeforeMs = dateBefore.getTime();
            long dateAfterMs = dateAfter.getTime();
            long timeDiff = dateAfterMs - dateBeforeMs;

            long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
            if (daysDiff>=1){
                reservation.get().setCancled(true);
                reservationRepository.save(reservation.get());
                return true;
            } return false;
        }
        throw new BadRequestException("Reservation doesn't exist.");
    }
}
