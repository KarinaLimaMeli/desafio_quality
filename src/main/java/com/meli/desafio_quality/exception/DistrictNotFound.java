package com.meli.desafio_quality.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DistrictNotFound extends RuntimeException {
    public DistrictNotFound(String message) {
        super(message);
    }
}
