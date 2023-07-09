package com.reservation.reservationservice.dtos;

import com.reservation.reservationservice.model.Price;
import com.reservation.reservationservice.model.enums.PriceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private String startDate;
    private String endDate;


    public  PriceDTO(Price price) {
        this.id = price.getId();
        this.dateFrom = price.getDateFrom();
        this.dateTo = price.getDateTo();
        this.price = price.getPrice();
        this.status = price.getStatus();
        this.accomodationId = price.getAccomodationId();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.startDate =dateFormat.format(price.getDateFrom());
        if(price.getDateTo()!=null){
            this.endDate =dateFormat.format(price.getDateTo());
        }
    }

    public void convertDate() {
        try {
            this.setDateFrom(new SimpleDateFormat("yyyy-MM-dd").parse(this.getStartDate()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
