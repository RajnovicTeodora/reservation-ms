package com.reservation.reservationservice.dtos;

import com.reservation.reservationservice.model.Unavilability;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UnavilabilityDTO {
    private String id;
    private Date dateFrom;
    private Date dateTo;
    private String accomodationId;


    public  UnavilabilityDTO(Unavilability unavilability) {
        this.id = unavilability.getId();
        this.dateFrom = unavilability.getDateFrom();
        this.dateTo = unavilability.getDateTo();
    }
}
