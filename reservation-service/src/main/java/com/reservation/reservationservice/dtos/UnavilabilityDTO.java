package com.reservation.reservationservice.dtos;

import com.reservation.reservationservice.model.Unavilability;
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
public class UnavilabilityDTO {
    private String id;
    private Date dateFrom;
    private Date dateTo;
    private String accomodationId;
    private String startDate;
    private String endDate;


    public  UnavilabilityDTO(Unavilability unavilability) {
        this.id = unavilability.getId();
        this.dateFrom = unavilability.getDateFrom();
        this.dateTo = unavilability.getDateTo();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.startDate =dateFormat.format(unavilability.getDateFrom());

        if(unavilability.getDateTo()!=null){
            this.endDate =dateFormat.format(unavilability.getDateTo());
        }
    }
    public void convertDate() {
        try {
            System.out.println(this.getStartDate());
            this.setDateFrom(new SimpleDateFormat("yyyy-MM-dd").parse(this.getStartDate()));
            System.out.println(this.getDateFrom());
            this.setDateTo(new SimpleDateFormat("yyyy-MM-dd").parse(this.getEndDate()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
