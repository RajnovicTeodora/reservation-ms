package com.reservation.reservationservice.controller;

import com.reservation.reservationservice.dtos.ReservationDTO;
import com.reservation.reservationservice.dtos.TableRequestDTO;
import com.reservation.reservationservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @ResponseBody
    @PutMapping(path = "/cancelReservation/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean cancelReservation (@PathVariable String id) throws Exception{
        return  reservationService.cancelReservation(id);
    }
    @ResponseBody
    @GetMapping(path = "/getByUserId/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationDTO> getReservationsByUser (@PathVariable String username) throws Exception{

        return reservationService.getRequestByUser(username);
    }
}
