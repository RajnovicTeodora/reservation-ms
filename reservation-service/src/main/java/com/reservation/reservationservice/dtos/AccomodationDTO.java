package com.reservation.reservationservice.dtos;

import com.reservation.reservationservice.model.Accomodation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccomodationDTO {

    private String Id;
    private String name;
    private List<String> listPhotos;
    private AddressDTO addres;
    private String benefits;
    private int maxGuest;
    private int minGuest;
    private boolean automaticApproval;

    public  AccomodationDTO(Accomodation accomodation) {
        this.addres = new AddressDTO(accomodation.getAddres());
        this.Id = accomodation.getId();
        this.maxGuest = accomodation.getMaxGuest();
        this.minGuest = accomodation.getMinGuest();
        this.listPhotos = accomodation.getListPhotos();
        this.name = accomodation.getName();
        this.benefits = accomodation.getBenefits();
        this.automaticApproval = accomodation.isAutomaticApproval();

    }
}

