package com.meli.desafio_quality.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class District {

    private String districtName;

    private BigDecimal valueDistrictM2;

}
