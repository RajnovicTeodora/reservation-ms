package com.reservation.reservationservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bad request")
public class BadRequestException extends Exception{

    private static final long serialVersionUID = 1L;
    public BadRequestException(String message) {
        super(message);
    }

}
