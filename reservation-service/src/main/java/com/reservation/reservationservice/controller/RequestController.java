package com.reservation.reservationservice.controller;

import com.reservation.reservationservice.dtos.ReservationDTO;
import com.reservation.reservationservice.dtos.TableRequestDTO;
import com.reservation.reservationservice.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @ResponseBody
    @PutMapping(path = "/approveRequest/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationDTO approveRequest (@PathVariable String id) throws Exception{
        return requestService.approveRequest(id);
    }
    @ResponseBody
    @PutMapping(path = "/declineRequest/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean declineRequest (@PathVariable String id) throws Exception{
        return requestService.declineRequest(id);
    }

    @ResponseBody
    @GetMapping(path = "/getRequestsByAccomodationId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TableRequestDTO> getRequests (@PathVariable String id) throws Exception{
        return requestService.getRequestsById(id);
    }
}
