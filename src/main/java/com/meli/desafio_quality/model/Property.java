package com.meli.desafio_quality.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {

    @NotBlank(message = "O nome da propriedade não pode estar vazio.")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$",
            message = "O nome da propriedade deve começar com letra maiúscula.")
    @Size(max = 30, message = "O comprimento não pode exceder 30 caracteres.")
    private String propertyName;

    private District district;

    @NotEmpty
    private List<@Valid Room> roomList;

}
