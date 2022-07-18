package com.meli.desafio_quality.handlerException;

import com.meli.desafio_quality.exception.ErrorResponseDetails;
import com.meli.desafio_quality.exception.PropertyNameNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerPropertyException {

    @ExceptionHandler(PropertyNameNotFound.class)
    public ResponseEntity<ErrorResponseDetails> handlerPropertyNameNotFound(PropertyNameNotFound ex) {
        return new ResponseEntity<>(
                ErrorResponseDetails.builder()
                        .title("Propriedade não encontrada")
                        .status(HttpStatus.NOT_FOUND.value())
                        .link("https://http.dog/404")
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(), HttpStatus.NOT_FOUND);
    }
}
