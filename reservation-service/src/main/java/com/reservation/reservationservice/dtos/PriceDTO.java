package com.reservation.reservationservice.dtos;

import com.reservation.reservationservice.model.Price;
import com.reservation.reservationservice.model.enums.PriceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PriceDTO {

    private String id;
    private Date dateFrom; //pitaj da li da stavim local
    private Date dateTo;
    private double price;
    private PriceStatus status;
    private String accomodationId;


    public  PriceDTO(Price price) {
        this.id = price.getId();
        this.dateFrom = price.getDateFrom();
        this.dateTo = price.getDateTo();
        this.price = price.getPrice();
        this.status = price.getStatus();
        this.accomodationId = price.getAccomodationId();
    }
}
