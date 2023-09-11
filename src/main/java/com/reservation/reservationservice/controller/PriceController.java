package com.reservation.reservationservice.controller;


import com.reservation.reservationservice.dtos.PriceDTO;
import com.reservation.reservationservice.exceptions.BadRequestException;
import com.reservation.reservationservice.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
    @ResponseBody
    @GetMapping(path = "/getListPricesForAccomodation/{accomodationId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PriceDTO> getPrices (@PathVariable String accomodationId) throws Exception{
        return   priceService.getListPricesForAccomodation(accomodationId);
    }
}
