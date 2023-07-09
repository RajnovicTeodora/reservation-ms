package com.reservation.reservationservice.dtos;

import com.reservation.reservationservice.model.Guest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GuestDTO {

    private String id;


    public GuestDTO(Guest guest) {
        this.id = guest.getId();
    }
}
