package com.reservation.reservationservice.model;

import com.reservation.reservationservice.dtos.PriceDTO;
import com.reservation.reservationservice.model.enums.PriceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "price")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Price extends DateRange{

    private String accomodationId;
    private double price;
    private PriceStatus status; // promeniti naziv

    public Price(PriceDTO priceDTO) {
        this.setDateFrom(priceDTO.getDateFrom());
        this.accomodationId = priceDTO.getAccomodationId();
        this.price = priceDTO.getPrice();
        this.status = priceDTO.getStatus();
    }
}
