package com.meli.desafio_quality.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class Room {

    @NotBlank(message = "O campo não pode estar vazio.")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$",
            message = "O nome do cômodo deve começar com letra maiúscula.")
    @Size(max = 30, message = "O comprimento do cômodo não pode exceder 30 caracteres.")
    private String roomName;

    @NotNull(message = "O campo não pode estar vazio.")
    @DecimalMax(value = "25.0", message = "A largura máxima permitida por cômodo é de 25 metros.")
    private double width;

    @NotNull(message = "O comprimento do cômodo não pode estar vazio.")
    @DecimalMax(value = "33.0", message = "O comprimento máxima permitida por cômodo é de 33 metros.")
    private double length;

    private double totalArea;

}
