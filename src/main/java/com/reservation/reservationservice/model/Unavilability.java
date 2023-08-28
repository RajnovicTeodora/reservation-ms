package com.reservation.reservationservice.model;

import com.reservation.reservationservice.dtos.UnavilabilityDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Document(value = "unavilability")
@TypeAlias("unavilability")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Unavilability extends DateRange {

    private String accomodationId;

    public Unavilability(UnavilabilityDTO dto){
        if(dto.getDateFrom()==null){

            try {
                this.setDateFrom(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getStartDate()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }else{
            this.setDateFrom(dto.getDateFrom());
        }
        if(dto.getDateTo()==null){

            try {
                if(dto.getEndDate()!=null){
                    this.setDateFrom(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getEndDate()));
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }else {
            this.setDateTo(dto.getDateTo());
        }
        this.accomodationId = dto.getAccomodationId();
    }

    public Unavilability(String number, String number1, Date date, Date date1) {
        super(number, date, date1);
        this.accomodationId = number1;
    }
}
