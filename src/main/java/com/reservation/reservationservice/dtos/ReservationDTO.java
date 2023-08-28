package com.reservation.reservationservice.dtos;

import com.reservation.reservationservice.model.Accomodation;
import com.reservation.reservationservice.model.Guest;
import com.reservation.reservationservice.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReservationDTO {
    private String Id;
    private Date dateFrom; //pitaj da li da stavim local
    private Date dateTo;
    private int guestNumber;
    private GuestDTO guest;  //todo
    private Accomodation accomodation;
    private boolean isDeleted;
    private boolean isCancled;

    public ReservationDTO(Reservation reservation) {
        this.Id = reservation.getId();
        this.dateFrom = reservation.getDateFrom();
        this.dateTo = reservation.getDateTo();
        this.guestNumber = reservation.getGuestNumber();
        this.guest = new GuestDTO(reservation.getGuest());
        this.accomodation = reservation.getAccomodation();
        this.isDeleted = reservation.isDeleted();
        this.isCancled = reservation.isCancled();
    }
}
