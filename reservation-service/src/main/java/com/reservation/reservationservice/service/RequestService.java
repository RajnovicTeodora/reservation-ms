package com.reservation.reservationservice.service;

import com.reservation.reservationservice.dtos.NewRequestDTO;
import com.reservation.reservationservice.dtos.RequestDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.model.DateRange;
import com.reservation.reservationservice.model.Request;
import com.reservation.reservationservice.model.Reservation;
import com.reservation.reservationservice.repository.AccomodationRepository;
import com.reservation.reservationservice.repository.RequestRepository;
import com.reservation.reservationservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private AccomodationRepository accomodationRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    public RequestDTO save(NewRequestDTO requestDTO) throws BadRequestException {
        List<Reservation> reservations = this.reservationRepository.findByAccomodationId(requestDTO.getAccomodationId());
        for(Reservation reservation : reservations){
            if(!chackDaysRange(requestDTO,reservation)){
                throw new BadRequestException("Reservation for that time exists.");
            }
        }
        Request request = new Request(requestDTO, accomodationRepository.findById(requestDTO.getAccomodationId()));
        RequestDTO createdRequest = new RequestDTO(requestRepository.save(request));

        if(accomodationRepository.findById(requestDTO.getAccomodationId()).get().isAutomaticApproval()){//todo
            Reservation newReservation= new Reservation(request, null, accomodationRepository.findById(requestDTO.getAccomodationId()).get());
            reservationRepository.save(newReservation);
        }
        return createdRequest;

    }

    public boolean chackDaysRange(NewRequestDTO requestDTO, DateRange existingDateRange){
        return requestDTO.getDateFrom().after(existingDateRange.getDateTo()) ||
                requestDTO.getDateTo().before(existingDateRange.getDateFrom());
    }

    public boolean deleteRequest(String requestId) {
        Optional<Request> request = this.requestRepository.findById(requestId);
        if(request.isPresent()){
            Reservation reservation =this.reservationRepository.findByAccomodationIdAndDateFromAndDateTo(request.get().getAccomodation().getId(), request.get().getDateFrom(), request.get().getDateTo());
            if(reservation!=null){
                return false;
            }
            request.get().setDeleted(true);
            this.requestRepository.save(request.get());
            return true;
        }
        return false;

    }
}
