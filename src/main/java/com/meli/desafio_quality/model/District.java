package com.meli.desafio_quality.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class District {

    @NotBlank(message = "O bairro não pode estar vazio.")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres.")
    private String districtName;

    @NotBlank(message = "O valor do metro quadrado não pode estar vazio.")
    private BigDecimal valueDistrictM2;

}