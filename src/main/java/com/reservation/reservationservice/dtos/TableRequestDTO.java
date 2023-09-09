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
public class TableRequestDTO {
    private String id;
    private Date dateFrom;
    private Date dateTo;
    private int guestNumber;
    private RequestStatus requestStatus;
    private boolean isDeleted;
    private String accomodationName;
    private String username;
    private int canceledNumber;

    public TableRequestDTO(Request request, String username, int numberOfCancelation, String accomodationName) { //todo datumi u neki utils
        this.id = request.getId();
        this.dateFrom = request.getDateFrom();
        this.dateTo = request.getDateTo();
        this.guestNumber = request.getGuestNumber();
        this.requestStatus = request.getRequestStatus();
        this.isDeleted = request.isDeleted();
        this.accomodationName = accomodationName;
        this.username = username;
        this.canceledNumber = numberOfCancelation;
    }
}

