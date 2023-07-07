package com.reservation.reservationservice.model;

import com.reservation.reservationservice.model.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(value = "request")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Request extends DateRange{
    private int guestNumber;
    private RequestStatus requestStatus;
    private boolean isDeleted;
    private String accomodationId;
    private String guestId;

    public Request(String number, Date date1, Date date2, int i, RequestStatus requestStatus, boolean b, String number1, String number2) {
        super(number, date1, date2);
        this.guestNumber = i;
        this.requestStatus = requestStatus;
        this.isDeleted = b;
        this.accomodationId = number1;
        this.guestId = number2;

    }
}
