package com.reservation.reservationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "reservation")
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

    public Reservation(Request request, Guest guest, Accomodation accomodation) {
        super(null, request.getDateFrom(), request.getDateTo());
        this.guestNumber = request.getGuestNumber();
        this.guest = guest;
        this.isDeleted = false;
        this.isCancled = false;
    }
}
