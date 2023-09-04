package com.reservation.reservationservice.controller;


import com.reservation.reservationservice.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservation.reservationservice.dtos.AccomodationDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.service.AccomodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/accomodation")
public class AccomodationController {
    @Autowired
    private AccomodationService accomodationService;

    @ResponseBody
    @PostMapping(path = "/addAccomodation")
    @ResponseStatus(HttpStatus.OK)
    public AccomodationDTO saveAccomodation (@RequestBody AccomodationDTO accomodation) throws Exception{
        if(accomodation.getId()!=null){
            throw new BadRequestException("Accommodation already has id.");
        }
        return accomodationService.save(accomodation);
    }

    @ResponseBody
    @GetMapping(path = "/deleteAccommodations/{hostUsername}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteAccommodations (@PathVariable String hostUsername){
        try {
            accomodationService.deleteAccommodations(hostUsername);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>("Host not found", HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @GetMapping(path = "/getHostUsernameByAccId/{accId}/{isName}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getHostUsernameByAccId (@PathVariable String accId, @PathVariable boolean isName){
        try {
            return new ResponseEntity<>(accomodationService.getHostUsernameByAccId(accId, isName), HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>("Accommodation not found", HttpStatus.NOT_FOUND);
        }
    }
}
