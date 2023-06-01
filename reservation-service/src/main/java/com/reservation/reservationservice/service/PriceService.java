package com.reservation.reservationservice.service;

import com.reservation.reservationservice.dtos.PriceDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.model.Price;
import com.reservation.reservationservice.model.Reservation;
import com.reservation.reservationservice.repository.AccomodationRepository;
import com.reservation.reservationservice.repository.PriceRepository;
import com.reservation.reservationservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private AccomodationRepository accomodationRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    public PriceDTO save(PriceDTO priceDTO) throws BadRequestException {
        if(priceDTO.getDateFrom().after(new Date())){
            throw new BadRequestException("Price shoould be valid in future."); //treba da pise cena vazi od
        }

        for(Reservation reservation : reservationRepository.findByAccomodationId(priceDTO.getAccomodationId())){
            if(reservation.getDateTo().after(priceDTO.getDateFrom())){
                throw new BadRequestException("There is alredy reservation with old price. Input date after "+reservation.getDateTo().toString());
            }
        }
        Price price  = new Price(priceDTO);
        PriceDTO newPrice = new PriceDTO(priceRepository.save(price));
        if(!priceRepository.findByAccomodationId(priceDTO.getAccomodationId()).isEmpty()){
           Price oldPrice = priceRepository.findByAccomodationIdAndDateToIsNull(priceDTO.getAccomodationId());
           oldPrice.setDateTo(priceDTO.getDateFrom());
           priceRepository.save(oldPrice);
        }
        return newPrice;
    }
}
