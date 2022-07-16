package com.meli.desafio_quality.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
public class ErrorBodyValidationsResponse {
    private String title;
    private String message;
    private String fields;
    private LocalDateTime timestamp;
}
