package com.reservation.reservationservice.controller;

import com.reservation.reservationservice.dtos.NewRequestDTO;
import com.reservation.reservationservice.dtos.RequestDTO;
import com.reservation.reservationservice.dtos.TableRequestDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
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
    @PostMapping(path = "/addRequest")
    @ResponseStatus(HttpStatus.OK)
    public RequestDTO saveRequest (@RequestBody NewRequestDTO request) throws Exception{
        if(request.getId()!=null){
            throw new BadRequestException("Request already have id.");
        }
        return requestService.save(request);
    }

    @ResponseBody
    @DeleteMapping(path = "/deleteRequest/{requestId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteRequest (@PathVariable String requestId) throws Exception{

        return requestService.deleteRequest(requestId);
    }
    @ResponseBody
    @GetMapping(path = "/getByUserId/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TableRequestDTO> getRequestsByUser (@PathVariable String userId) throws Exception{
        return requestService.getRequestByUser(userId);
    }

}
