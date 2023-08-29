package com.reservation.reservationservice.controller;

import com.reservation.reservationservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
