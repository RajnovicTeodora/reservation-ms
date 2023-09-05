package com.reservation.reservationservice.controller;

import com.reservation.reservationservice.dtos.AccomodationDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController("guestsController")
@RequestMapping("/api/guest")
public class GuestController {
    @Autowired
    private GuestService guestService;

    @ResponseBody
    @PostMapping(path = "/addGuest/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void saveGuest (@PathVariable String username) throws Exception{

        guestService.save(username);
    }
}
