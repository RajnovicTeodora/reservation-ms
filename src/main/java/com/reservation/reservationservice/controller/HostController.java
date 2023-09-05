package com.reservation.reservationservice.controller;

import com.reservation.reservationservice.service.GuestService;
import com.reservation.reservationservice.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/host")
public class HostController {

    @Autowired
    private HostService hostService;

    @ResponseBody
    @PostMapping(path = "/addHost/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void saveHost (@PathVariable String username) throws Exception{
        hostService.save(username);
    }
}
