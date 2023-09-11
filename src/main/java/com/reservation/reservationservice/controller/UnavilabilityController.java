package com.reservation.reservationservice.controller;


import com.reservation.reservationservice.dtos.PriceDTO;
import com.reservation.reservationservice.dtos.UnavilabilityDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.service.UnavilabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/unavilability")
public class UnavilabilityController {


    @Autowired
    private UnavilabilityService unavilabilityService;

    @ResponseBody
    @PostMapping(path = "/addUnavilability")
    @ResponseStatus(HttpStatus.OK)
    public UnavilabilityDTO saveUnavilability (@RequestBody UnavilabilityDTO unavilability) throws Exception{
        if(unavilability.getId()!=null){
            throw new BadRequestException("Unavilability already have id.");
        }

        return  unavilabilityService.save(unavilability);
    }
    @ResponseBody
    @GetMapping(path = "/getListUnavilabilitiesForAccomodation/{accomodationId}")
    @ResponseStatus(HttpStatus.OK)
    public List<UnavilabilityDTO> getUnavilability (@PathVariable String accomodationId) throws Exception{

        return   unavilabilityService.getListUnavilabilitiesForAccomodation(accomodationId);
    }
}
