package com.meli.desafio_quality.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class District {
    private String districtName;
    private BigDecimal valueDistrictM2;
}
