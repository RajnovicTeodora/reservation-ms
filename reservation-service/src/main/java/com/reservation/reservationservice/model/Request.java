package com.reservation.reservationservice.model;

import com.reservation.reservationservice.model.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "request")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Request extends DateRange{
    private int guestNumber;
    private RequestStatus requestStatus;
    private boolean isDeleted;
    private Accomodation accomodation;
}
