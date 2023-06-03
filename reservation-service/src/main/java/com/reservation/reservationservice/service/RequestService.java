package com.reservation.reservationservice.service;

import com.reservation.reservationservice.dtos.ReservationDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.model.DateRange;
import com.reservation.reservationservice.model.Request;
import com.reservation.reservationservice.model.Reservation;
import com.reservation.reservationservice.model.enums.RequestStatus;
import com.reservation.reservationservice.repository.AccomodationRepository;
import com.reservation.reservationservice.repository.RequestRepository;
import com.reservation.reservationservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private AccomodationRepository accomodationRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    public ReservationDTO approveRequest(String id) throws BadRequestException {

        Optional<Request> request =  this.requestRepository.findById(id);
        if(request.isPresent()){
            Reservation reservation = new Reservation(request.get(), null); //todo
            request.get().setRequestStatus(RequestStatus.APPROVED);

            for(Request otherRequest : this.requestRepository.findAllByAccomodationId(request.get().getAccomodation().getId())){
                if(!Objects.equals(otherRequest.getId(), request.get().getId())){
                    if(!chackDaysRangeDateRange(request.get(), otherRequest)){
                        otherRequest.setRequestStatus(RequestStatus.DECLINED);
                        this.requestRepository.save(otherRequest);
                    }
                }
            }
            return new ReservationDTO(this.reservationRepository.save(reservation));
        }
        throw new BadRequestException("There is no request with that id");
    }

    public boolean chackDaysRangeDateRange(DateRange request, DateRange existingDateRange){
        return request.getDateFrom().after(existingDateRange.getDateTo()) ||
                request.getDateTo().before(existingDateRange.getDateFrom());
    }
}
