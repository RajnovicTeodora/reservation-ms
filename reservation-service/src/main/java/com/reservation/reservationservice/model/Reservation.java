package com.reservation.reservationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "reservation")
@TypeAlias("reservation")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Reservation extends DateRange{
    private int guestNumber;
    private Guest guest;  //da imam samo jednog gosta koji je rezervisao ovo u jednom trebutku
    private Accomodation accomodation;
    private boolean isDeleted;
    private boolean isCancled;


    public Reservation(Request request, Guest guest) {
        super(null, request.getDateFrom(), request.getDateTo());
        this.guestNumber = request.getGuestNumber();
        this.guest = guest;
        this.isDeleted = false;
        this.isCancled = false;
    }

    public Reservation(Request request, Guest guest, Accomodation accommodation) {
        super(null, request.getDateFrom(), request.getDateTo());
        this.guestNumber = request.getGuestNumber();
        this.guest = guest;
        this.isDeleted = false;
        this.isCancled = false;
        this.accomodation = accommodation;
    }
}
