package com.reservation.reservationservice.service;

import com.reservation.reservationservice.dtos.ReservationDTO;
import com.reservation.reservationservice.dtos.TableRequestDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.model.Accomodation;
import com.reservation.reservationservice.model.Guest;
import com.reservation.reservationservice.model.Request;
import com.reservation.reservationservice.model.Reservation;
import com.reservation.reservationservice.model.enums.RequestStatus;
import com.reservation.reservationservice.repository.GuestRepository;
import com.reservation.reservationservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private GuestRepository guestRepository;

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
            } throw new BadRequestException("Cant cancel one day before.");
        }
        throw new BadRequestException("Reservation doesn't exist.");
    }

    public List<ReservationDTO> getRequestByUser(String username) throws BadRequestException {
        Guest guest = (Guest) guestRepository.findByUsername(username).orElseThrow(() -> new BadRequestException("Guest not found with username: " + username));
        List<Reservation> filteredRequests = reservationRepository.findAllByGuestId(guest.getId());
        List<ReservationDTO> dtos = filteredRequests.stream()
                .filter(r -> !r.isDeleted() && !r.isCancled())
                .map(r ->  new ReservationDTO(r))
                .collect(Collectors.toList());
        return dtos;
    }
}
