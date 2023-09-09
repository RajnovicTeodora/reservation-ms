package com.reservation.reservationservice.dtos;

import com.reservation.reservationservice.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddressDTO {

    private String Id;
    private String street;
    private String number;
    private String city;

    public AddressDTO(Address addres) {
        this.city = addres.getCity();
        this.number = addres.getNumber();
        this.street  = addres.getStreet();
        this.Id = addres.getId();
    }
}
