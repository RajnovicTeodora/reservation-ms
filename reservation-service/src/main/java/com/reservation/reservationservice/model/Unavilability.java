package com.reservation.reservationservice.model;

import com.reservation.reservationservice.dtos.UnavilabilityDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "unavilability")
public class Unavilability extends DateRange {

    public Unavilability(UnavilabilityDTO dto){
        this.setDateFrom(dto.getDateFrom());
        this.setDateTo(dto.getDateTo());
    }
}
