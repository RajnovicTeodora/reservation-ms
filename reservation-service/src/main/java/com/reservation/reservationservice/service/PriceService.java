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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private AccomodationRepository accomodationRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    public PriceDTO save(PriceDTO priceDTO) throws BadRequestException {
        if(priceDTO.getDateFrom()==null){
            priceDTO.convertDate();
        }
        if(!priceDTO.getDateFrom().after(new Date())){
            throw new BadRequestException("Price shoould be valid in future."); //treba da pise cena vazi od

        }

        for(Reservation reservation : reservationRepository.findByAccomodation(priceDTO.getAccomodationId())){
            if(reservation.getDateTo().after(priceDTO.getDateFrom())){ System.out.println("lll");
                throw new BadRequestException("There is alredy reservation with old price. Input date after "+reservation.getDateTo().toString());
            }
        }

        if(!priceRepository.findByAccomodationId(priceDTO.getAccomodationId()).isEmpty()){
               Price oldPrice = priceRepository.findByAccomodationIdAndDateToIsNull(priceDTO.getAccomodationId());
               oldPrice.setDateTo(priceDTO.getDateFrom());
               priceRepository.save(oldPrice);
               Price price  = new Price(priceDTO);
               return new PriceDTO(priceRepository.save(price));

        }else{
            Price price  = new Price(priceDTO);
            return new PriceDTO(priceRepository.save(price));
        }
    }

    public List<PriceDTO> getListPricesForAccomodation(String accomodationId) {
        List<Price> prices = this.priceRepository.findByAccomodationId(accomodationId);
        List<PriceDTO> pricesDTO = new ArrayList<>();
        for(Price p : prices){
            pricesDTO.add(new PriceDTO(p));
        }
        return pricesDTO;

    }
}
