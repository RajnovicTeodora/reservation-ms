package com.reservation.reservationservice.controller;

import com.reservation.reservationservice.dtos.ReservationDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.exceptions.NotFoundException;
import com.reservation.reservationservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @ResponseBody
    @PutMapping(path = "/cancelReservation")
    @ResponseStatus(HttpStatus.OK)
    public boolean cancelReservation (@RequestBody String id) throws Exception{
        return  reservationService.cancelReservation(id);
    }

    @ResponseBody
    @GetMapping(path = "/checkActiveReservations/{username}/{userType}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> checkActiveReservations (@PathVariable String username, @PathVariable String userType){
        try {
            return new ResponseEntity<>(reservationService.checkActiveReservations(username, userType), HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @GetMapping(path = "/checkFinishedReservations/{hostUsername}/{guestUsername}/{accommodationId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> checkFinishedReservations (@PathVariable String hostUsername,
                                                        @PathVariable String guestUsername,
                                                        @PathVariable String accommodationId){
        try {
            return new ResponseEntity<>(reservationService.
                    checkFinishedReservations(hostUsername, guestUsername, accommodationId), HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }catch (BadRequestException e){
            return new ResponseEntity<>("Accommodation not found", HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @GetMapping(path = "/updateUsername/{oldUsername}/{newUsername}/{userType}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateUsername (@PathVariable String oldUsername, @PathVariable String newUsername,
                                             @PathVariable String userType){
        try {
            reservationService.updateUsername(oldUsername, newUsername, userType);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
    @ResponseBody
    @GetMapping(path = "/getByUserId/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationDTO> getReservationsByUser (@PathVariable String username) throws Exception{

        return reservationService.getRequestByUser(username);
    }
}
