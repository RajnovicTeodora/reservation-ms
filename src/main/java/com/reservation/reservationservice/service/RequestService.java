package com.reservation.reservationservice.service;
import com.reservation.reservationservice.dtos.ReservationDTO;
import com.reservation.reservationservice.dtos.NewRequestDTO;
import com.reservation.reservationservice.dtos.RequestDTO;

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
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

        Request request =  this.requestRepository.findById(id).orElseThrow(() ->new BadRequestException("There is no request with that id"));

        Reservation reservation = new Reservation(request, guestRepository.findById(request.getGuestId()).get());
        request.setRequestStatus(RequestStatus.APPROVED);
        this.requestRepository.save(request);

        for(Request otherRequest : this.requestRepository.findAllByAccomodationId(request.getAccomodationId())){
            if(!Objects.equals(otherRequest.getId(), request.getId())){
                if(!chackDaysRangeDateRange(request, otherRequest)){
                    otherRequest.setRequestStatus(RequestStatus.DECLINED);
                    this.requestRepository.save(otherRequest);
                }
            }
        }
        return new ReservationDTO(this.reservationRepository.save(reservation));

    }

    public boolean chackDaysRangeDateRange(DateRange request, DateRange existingDateRange){
        return request.getDateFrom().after(existingDateRange.getDateTo()) ||
                request.getDateTo().before(existingDateRange.getDateFrom());
    }

    public List<TableRequestDTO> getRequestsById(String id) {
        List<TableRequestDTO> dtos = requestRepository.findAllByAccomodationId(id).stream()
                .filter(r -> !r.isDeleted() && r.getRequestStatus() == RequestStatus.PENDING)
                .map(r -> {
                    Guest guest = guestRepository.findById(r.getGuestId()).get();
                    Accomodation accomodation = accomodationRepository.findById(r.getAccomodationId()).get();
                    return new TableRequestDTO(r, "", guest.getCanceldReservations(),accomodation.getName());//todo
                })
                .collect(Collectors.toList());

        return dtos;
    }

    public boolean declineRequest(String id) throws BadRequestException {
        System.out.println(id);
        Optional<Request> request = this.requestRepository.findById(id);
        if (request.isPresent()) {
            request.ifPresent(value -> value.setRequestStatus(RequestStatus.DECLINED));
            requestRepository.save(request.get());
            System.out.println(request);
            return true;
        }
        throw new BadRequestException("There is no request with that id");
    }

    public RequestDTO save(NewRequestDTO requestDTO) throws BadRequestException {
        List<Reservation> reservations = this.reservationRepository.findByAccomodationId(requestDTO.getAccomodationId());
        for(Reservation reservation : reservations){
            if(!chackDaysRange(requestDTO,reservation)){
                throw new BadRequestException("Reservation for that time exists.");
            }
        }
        Request request = new Request(requestDTO, accomodationRepository.findById(requestDTO.getAccomodationId()));
        Optional<Accomodation> accomodation = accomodationRepository.findById(requestDTO.getAccomodationId());
        if (accomodation.isPresent()){
            if (request.getGuestNumber()> accomodation.get().getMaxGuest() ){
                throw new BadRequestException("In this accomodation maximum number of guest is "+accomodation.get().getMaxGuest());
            }
            if(accomodationRepository.findById(requestDTO.getAccomodationId()).get().isAutomaticApproval()){
                Reservation newReservation= new Reservation(request, null, accomodationRepository.findById(requestDTO.getAccomodationId()).get()); //todo guest
                reservationRepository.save(newReservation);
                request.setRequestStatus(RequestStatus.APPROVED);
            }
        }

        return  new RequestDTO(requestRepository.save(request), accomodationRepository.findById(requestDTO.getAccomodationId()).get().getName());
    }

    public boolean chackDaysRange(NewRequestDTO requestDTO, DateRange existingDateRange){
        return requestDTO.getDateFrom().before(existingDateRange.getDateTo()) &&
                requestDTO.getDateTo().after(existingDateRange.getDateFrom());
    }

    public boolean deleteRequest(String requestId) {
        Optional<Request> request = this.requestRepository.findById(requestId);
        if(request.isPresent()){
            Reservation reservation =this.reservationRepository.findByAccomodationIdAndDateFromAndDateTo(request.get().getAccomodationId(), request.get().getDateFrom(), request.get().getDateTo());
            if(reservation!=null){
                return false;
            }
            request.get().setDeleted(true);
            this.requestRepository.save(request.get());
            return true;
        }
        return false;

    }

    public List<TableRequestDTO> getRequestByUser(String userId) throws BadRequestException {

        List<Request> filteredRequests = requestRepository.findAllByGuestId(userId)
                .stream()
                .filter(r -> r.getRequestStatus() == RequestStatus.PENDING && !r.isDeleted())
                .collect(Collectors.toList());
        Guest guest = guestRepository.findById(userId).orElseThrow(() -> new BadRequestException("Guest not found with ID: " + userId));


        List<TableRequestDTO> dtos = filteredRequests.stream()
                .map(r -> new TableRequestDTO(r, "name", guest.getCanceldReservations(), "accName"))
                .collect(Collectors.toList());
        return dtos;

    }
}