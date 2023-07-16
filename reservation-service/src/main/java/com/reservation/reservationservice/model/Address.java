package com.reservation.reservationservice.model;

import com.reservation.reservationservice.dtos.AddressDTO;
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
    private String id;
    private String street;
    private String number;
    private String city;

    public Address(AddressDTO addres) {
        this.city = addres.getCity();
        this.number = addres.getNumber();
        this.street  = addres.getStreet();
        this.id = addres.getId();
    }
}
