package com.reservation.reservationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document(value = "data_range")
@AllArgsConstructor
@NoArgsConstructor
//@Builder
@Data
public abstract class DateRange {

    @Id
    private String Id;
    private Date dateFrom; //pitaj da li da stavim local
    private Date dateTo;

}
