package com.reservation.reservationservice.service;

import com.reservation.reservationservice.dtos.ReservationDTO;
import com.reservation.reservationservice.dtos.TableRequestDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.model.*;
import com.reservation.reservationservice.model.enums.RequestStatus;
import com.reservation.reservationservice.repository.AccomodationRepository;
import com.reservation.reservationservice.repository.GuestRepository;
import com.reservation.reservationservice.repository.RequestRepository;
import com.reservation.reservationservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private AccomodationRepository accomodationRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private GuestRepository guestRepository;
    public ReservationDTO approveRequest(String id) throws BadRequestException {

        Optional<Request> request =  this.requestRepository.findById(id);
        if(request.isPresent()){
            Reservation reservation = new Reservation(request.get(), guestRepository.findById(request.get().getGuestId()).get()); //todo
            request.get().setRequestStatus(RequestStatus.APPROVED);
            this.requestRepository.save(request.get());

            for(Request otherRequest : this.requestRepository.findAllByAccomodationId(request.get().getAccomodationId())){
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

    public List<TableRequestDTO> getRequestsById(String id) {
        List<TableRequestDTO> dtos = requestRepository.findAllByAccomodationId(id).stream()
                .filter(r -> !r.isDeleted() && r.getRequestStatus() == RequestStatus.PANDING)
                .map(r -> {
                    Guest guest = guestRepository.findById(r.getGuestId()).get();
                    Accomodation accomodation = accomodationRepository.findById(r.getAccomodationId()).get();
                    return new TableRequestDTO(r, "", guest.getCanceldReservations(),accomodation.getName());//todo
                })
                .collect(Collectors.toList());

        return dtos;
    }

    public boolean declineRequest(String id) {
        System.out.println(id);
        Optional<Request> request =  this.requestRepository.findById(id);

        request.ifPresent(value -> value.setRequestStatus(RequestStatus.DECLINED));
        requestRepository.save(request.get());
        System.out.println(request);
        return true;
    }
}
