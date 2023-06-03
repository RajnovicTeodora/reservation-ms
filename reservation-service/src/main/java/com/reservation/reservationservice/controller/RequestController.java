package com.reservation.reservationservice.controller;

import com.reservation.reservationservice.dtos.ReservationDTO;
import com.reservation.reservationservice.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @ResponseBody
    @PutMapping(path = "/approveRequest")
    @ResponseStatus(HttpStatus.OK)
    public ReservationDTO approveRequest (@RequestBody String id) throws Exception{
        return requestService.approveRequest(id);
    }
}
