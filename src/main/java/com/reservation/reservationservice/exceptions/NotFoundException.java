package com.reservation.reservationservice.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not found")
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public NotFoundException() {
    }
    public NotFoundException(String message) {
        super(message);
    }
}
