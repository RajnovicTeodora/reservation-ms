package com.reservation.reservationservice.dtos;

import com.reservation.reservationservice.model.Accomodation;
import com.reservation.reservationservice.model.Guest;
import com.reservation.reservationservice.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReservationDTO {
    private String Id;
    private Date dateFrom;
    private Date dateTo;
    private int guestNumber;
    private GuestDTO guest;
    private AccomodationDTO accomodation;
    private boolean isDeleted;
    private boolean isCancled;
    private String startDate;
    private String endDate;
    private String usernameHost;


    public ReservationDTO(Reservation reservation, Accomodation accomodation, String host) {
        this.Id = reservation.getId();
        this.dateFrom = reservation.getDateFrom();
        this.dateTo = reservation.getDateTo();
        this.guestNumber = reservation.getGuestNumber();
        this.guest = new GuestDTO(reservation.getGuest());
        this.isDeleted = reservation.isDeleted();
        this.isCancled = reservation.isCancled();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.accomodation = new AccomodationDTO(accomodation);
        this.startDate =dateFormat.format(dateFrom);
        this.endDate =dateFormat.format(dateTo);
        this.usernameHost = host;
    }
}
