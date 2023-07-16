package com.reservation.reservationservice.model;

import com.reservation.reservationservice.dtos.NewRequestDTO;
import com.reservation.reservationservice.model.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Optional;

@Document(value = "request")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Request extends DateRange{
    private int guestNumber;
    private RequestStatus requestStatus;
    private boolean isDeleted;
    private String accomodationId; //ovde ce mozda ici accId, saznacemo prilikom koriscenja
    private String guestId;

    public Request(NewRequestDTO requestDTO, Optional<Accomodation> accomodation) {
        super(null, requestDTO.getDateFrom(), requestDTO.getDateTo());
        accomodation.ifPresent(value -> this.accomodationId = value.getId());
        this.guestNumber = requestDTO.getGuestNumber();
        this.requestStatus = RequestStatus.PENDING;
        this.guestId = requestDTO.getGuestId();

    }
}
