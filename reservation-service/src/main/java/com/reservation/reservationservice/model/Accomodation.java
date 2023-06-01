package com.reservation.reservationservice.model;
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
    private List<String> benefits;
    private int maxGuest;
    private int minGuest;
    private boolean automaticApproval;
    private List<Unavilability> unavilabilities;




}
