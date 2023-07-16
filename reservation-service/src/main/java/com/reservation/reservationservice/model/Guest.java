package com.reservation.reservationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "guest")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Guest {

    @Id
    private String id;

    private int  canceldReservations; //samo one rezervacije koje je on sam otkazao

}
