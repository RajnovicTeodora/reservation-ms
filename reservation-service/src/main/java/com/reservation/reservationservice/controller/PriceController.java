package com.reservation.reservationservice.controller;

import com.reservation.reservationservice.dtos.PriceDTO;
import com.reservation.reservationservice.dtos.UnavilabilityDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.service.PriceService;
import com.reservation.reservationservice.service.UnavilabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/price")
public class PriceController {
    @Autowired
    private PriceService priceService;

    @ResponseBody
    @PostMapping(path = "/addPrice")
    @ResponseStatus(HttpStatus.OK)
    public PriceDTO savePrice (@RequestBody PriceDTO price) throws Exception{
        if(price.getId()!=null){
            throw new BadRequestException("Price already have id.");
        }
        return  priceService.save(price);
    }
}
