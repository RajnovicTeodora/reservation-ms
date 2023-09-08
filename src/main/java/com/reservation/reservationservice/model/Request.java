package com.reservation.reservationservice.model;

import com.reservation.reservationservice.dtos.NewRequestDTO;
import com.reservation.reservationservice.model.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

import java.util.Optional;


@Document(value = "request")
@AllArgsConstructor
@TypeAlias("request")
@NoArgsConstructor
@Builder
@Data
public class Request extends DateRange{
    private int guestNumber;
    private RequestStatus requestStatus;
    private boolean isDeleted;


    private String accomodationId; //ovde ce mozda ici accId, saznacemo prilikom koriscenja
    private String guestId;

    public Request(String number, Date date1, Date date2, int i, RequestStatus requestStatus, boolean b, String number1, String number2) {
        super(number, date1, date2);
        this.guestNumber = i;
        this.requestStatus = requestStatus;
        this.isDeleted = b;
        this.accomodationId = number1;
        this.guestId = number2;
    }

    public Request(NewRequestDTO requestDTO, Optional<Accomodation> accomodation) {
        super(null, requestDTO.getDateFrom(), requestDTO.getDateTo());
        accomodation.ifPresent(value -> this.accomodationId = value.getId());
        this.guestNumber = requestDTO.getGuestNumber();
        this.requestStatus = RequestStatus.PENDING;
        this.guestId = requestDTO.getGuestId();

    }
}
