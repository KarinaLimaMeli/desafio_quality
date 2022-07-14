package com.meli.desafio_quality.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DistrictAlreadyExist extends RuntimeException {
    public DistrictAlreadyExist(String message) {
        super(message);
    }
}
