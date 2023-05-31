package com.reservation.reservationservice.controller;

import com.reservation.reservationservice.dtos.AccomodationDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.service.AccomodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accomodation")
public class AccomodationController {

    @Autowired
    private AccomodationService accomodationService;
    private static final String ENTITY_NAME = "accomodation";
    private static final String applicationName = "reservation service";

    @ResponseBody
    @PostMapping(path = "/addAccomodation")
    @ResponseStatus(HttpStatus.OK)
    public AccomodationDTO saveAccomodation (@RequestBody AccomodationDTO accomodation) throws Exception{
        if(accomodation.getId()!=null){
            throw new BadRequestException("Accomodation already have id.");
        }
        return  accomodationService.save(accomodation);
    }

}
