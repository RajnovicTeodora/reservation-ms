package com.reservation.reservationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document(collection = "dataRange")
@TypeAlias("dateRange")
@AllArgsConstructor
@NoArgsConstructor
//@Builder
@Data
public abstract class DateRange {
    @Id
    private String id;
    private Date dateFrom; //pitaj da li da stavim local
    private Date dateTo;

}
