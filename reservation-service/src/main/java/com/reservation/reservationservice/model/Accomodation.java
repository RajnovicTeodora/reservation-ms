package com.reservation.reservationservice.model;
import com.reservation.reservationservice.dtos.AccomodationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "accomodation")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Accomodation {

    @Id
    private String Id;
    private String name;
    private List<String> listPhotos;
    private Address addres;
    private String benefits;
    private int maxGuest;
    private int minGuest;
    private boolean automaticApproval;


    public Accomodation(AccomodationDTO accomodation) {
        this.addres = new Address(accomodation.getAddres());
        this.Id = accomodation.getId();
        this.maxGuest = accomodation.getMaxGuest();
        this.minGuest = accomodation.getMinGuest();
        this.listPhotos = accomodation.getListPhotos();
        this.name = accomodation.getName();
        this.benefits = accomodation.getBenefits();
        this.automaticApproval = accomodation.isAutomaticApproval();

    }
}
