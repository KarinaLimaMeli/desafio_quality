package com.meli.desafio_quality.repository;


import com.meli.desafio_quality.exception.DistrictAlreadyExist;
import com.meli.desafio_quality.model.District;
import com.meli.desafio_quality.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DistrictRepositoryTest {

    private DistrictRepository districtRepository;

    @BeforeEach
    void setup() {
        districtRepository = new DistrictRepository();
    }

    @Test
    void createDistrict_whenNewDistrict() {
        District newDistrict = Util.allDistricts().get(0);

        District createdDistrict = districtRepository.createDistrict(newDistrict);

        assertThat(createdDistrict).isNotNull();
        assertThat(createdDistrict.getDistrictName())
                .isEqualTo(newDistrict.getDistrictName());
    }

    @Test
    void createDistrict_throwException_whenDistrictAlreadyExists() {
        District district = Util.allDistricts().get(0);
        districtRepository.createDistrict(district);
        DistrictAlreadyExist exception = Assertions.assertThrows(DistrictAlreadyExist.class, () -> {
            District createdDistrict = districtRepository.createDistrict(district);
        });

        assertThat(exception.getMessage()).isEqualTo("Bairro já cadastrado");

    }


}