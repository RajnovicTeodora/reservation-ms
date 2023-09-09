package com.reservation.reservationservice.dtos;

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
public class NewRequestDTO {
    private String id;
    private Date dateFrom; //pitaj da li da stavim local
    private Date dateTo;
    private int guestNumber;
    private RequestStatus requestStatus;
    private boolean isDeleted;
    private String accomodationId;
    //private String guestId;
    private String username;
}
