package com.meli.desafio_quality.model;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    @NotBlank(message = "O campo não pode estar vazio.")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$",
            message = "O nome do cômodo deve começar com letra maiúscula.")
    @Size(max = 30, message = "O comprimento do cômodo não pode exceder 30 caracteres.")
    private String roomName;

    @NotBlank(message = "O campo não pode estar vazio.")
    @Max(value = 25, message = "A largura máxima permitida por cômodo é de 25 metros.")
    private double width;

    @NotBlank(message = "O comprimento do cômodo não pode estar vazio.")
    @Max(value = 33, message = "O comprimento máxima permitida por cômodo é de 33 metros.")
    private double length;

    private double totalArea;

}
