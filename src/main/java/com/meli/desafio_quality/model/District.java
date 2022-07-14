package com.meli.desafio_quality.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class District {

    // MethodArgumentNotValidException
    @NotBlank(message = "O bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres.")
    private String districtName;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "o valor deve ser positivo")
    @Digits(integer = 6, fraction = 7, message = "")
    private BigDecimal valueDistrictM2;

}