package com.reservation.reservationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "address")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Address {
    @Id
    private String Id;
    private String street;
    private String number;
    private String city;

}
