package com.reservation.reservationservice.service;

import com.reservation.reservationservice.dtos.RatingDTO;
import com.reservation.reservationservice.dtos.ReservationDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.exceptions.NotFoundException;
import com.reservation.reservationservice.model.Accomodation;
import com.reservation.reservationservice.model.Guest;
import com.reservation.reservationservice.model.Host;
import com.reservation.reservationservice.model.Reservation;
import com.reservation.reservationservice.repository.AccomodationRepository;
import com.reservation.reservationservice.repository.GuestRepository;
import com.reservation.reservationservice.repository.HostRepository;
import com.reservation.reservationservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private AccomodationRepository accomodationRepository;

    public boolean cancelReservation(String id) throws BadRequestException {

        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()){
            Date dateBefore = new Date();
            Date dateAfter = reservation.get().getDateFrom();

            long dateBeforeMs = dateBefore.getTime();
            long dateAfterMs = dateAfter.getTime();
            long timeDiff = dateAfterMs - dateBeforeMs;

            long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
            if (daysDiff>=1){
                reservation.get().setCancled(true);
                reservationRepository.save(reservation.get());
                return true;
            } return false;
        }
        throw new BadRequestException("Reservation doesn't exist.");
    }

    public boolean checkActiveReservations(String username, String userType){
        if(userType.equals("GUEST")) {
            Optional<Guest> g = guestRepository.findGuestByUsername(username);
            if (!g.isPresent()) {
                throw new NotFoundException("User not found");
            }
            List<Reservation> reservations = reservationRepository.findByGuestIdAndIsCancledFalse(g.get().getId());
            boolean activeExist = false;
            for (Reservation r : reservations){
                if (r.getDateTo().after(new Date())) {
                    activeExist=true;
                    break;
                }
            }
            return activeExist;
        }else{
            Optional<Host> g = hostRepository.findHostByUsername(username);
            if (!g.isPresent()) {
                throw new NotFoundException("User not found");
            }
            boolean activeExist = false;
            for (Accomodation a : g.get().getAccomodations() ) {
                List<Reservation> reservations = reservationRepository.findByAccomodationIdAndIsCancledFalse(a.getId());
                for (Reservation r : reservations){
                    if (r.getDateTo().after(new Date())) {
                        activeExist=true;
                        break;
                    }
                }
            }
            return activeExist;
        }
    }

    public RatingDTO checkFinishedReservations(String hostUsername, String guestUsername, String accommodationId) throws BadRequestException{
        Optional<Guest> g = guestRepository.findGuestByUsername(guestUsername);
        if (!g.isPresent()) {
            throw new NotFoundException("User not found");
        }
        RatingDTO dto = new RatingDTO();
        //HOST RATING CHECK
        Optional<Host> h = hostRepository.findHostByUsername(hostUsername);
        if (!h.isPresent()) {
            throw new NotFoundException("User not found");
        }
        boolean previousExist = false;
        for (Accomodation a : h.get().getAccomodations() ) {
            List<Reservation> reservations = reservationRepository.
                    findByAccomodationIdAndGuestIdAndIsCancledFalse(a.getId(), g.get().getId());

            for (Reservation r : reservations){
                if (r.getDateTo().before(new Date())) {
                    previousExist=true;
                    break;
                }
            }
        }
        dto.setRateHost(previousExist);
        previousExist = false;
        //ACCOMMODATION RATING CHECK
        Optional<Accomodation> a = accomodationRepository.findAccomodationById(accommodationId);
        if(!a.isPresent()){
            throw new BadRequestException("Accommodation doesn't exist.");
        }
        List<Reservation> reservations = reservationRepository.
                findByAccomodationIdAndGuestIdAndIsCancledFalse(a.get().getId(), g.get().getId());
        for (Reservation r : reservations){
            if (r.getDateTo().before(new Date())) {
                previousExist=true;
                break;
            }
        }
        dto.setRateAcc(previousExist);
        return dto;
    }

    public void updateUsername(String oldUsername, String newUsername, String userType){
        if(userType.equals("GUEST")) {
            Optional<Guest> g = guestRepository.findGuestByUsername(oldUsername);
            if (!g.isPresent()) {
                throw new NotFoundException("User not found");
            }
            g.get().setUsername(newUsername);
            guestRepository.save(g.get());
        }else{
            Optional<Host> h = hostRepository.findHostByUsername(oldUsername);
            if (!h.isPresent()) {
                throw new NotFoundException("User not found");
            }
            h.get().setUsername(newUsername);
            hostRepository.save(h.get());
        }
    }

    public List<ReservationDTO> getRequestByUser(String username) throws BadRequestException {
        Guest guest = (Guest) guestRepository.findByUsername(username).orElseThrow(() -> new BadRequestException("Guest not found with username: " + username));
        List<Reservation> filteredRequests = reservationRepository.findAllByGuestId(guest.getId());
        List<ReservationDTO> dtos = filteredRequests.stream()
                .filter(r -> !r.isDeleted() && !r.isCancled())
                .map(r ->  new ReservationDTO(r,
                        accomodationRepository.findAccomodationById(r.getAccomodationId()).get().getHost().getUsername()))
                .collect(Collectors.toList());
        return dtos;
    }
}
