package com.reservation.reservationservice.dtos;

import com.reservation.reservationservice.model.Request;
import com.reservation.reservationservice.model.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RequestDTO {
    private String id;
    private Date dateFrom; //pitaj da li da stavim local
    private Date dateTo;
    private int guestNumber;
    private RequestStatus requestStatus;
    private boolean isDeleted;
    private AccomodationDTO accomodation;

    public RequestDTO(Request request) {
        this.id = request.getId();
        this.dateFrom = request.getDateFrom();
        this.dateTo = request.getDateTo();
        this.guestNumber = request.getGuestNumber();
        this.requestStatus = request.getRequestStatus();
        this.isDeleted = request.isDeleted();
        this.accomodation = new AccomodationDTO(request.getAccomodation());
    }
}
