package com.meli.desafio_quality.repository;


import com.meli.desafio_quality.exception.DistrictAlreadyExist;
import com.meli.desafio_quality.mocks.DistrictMocks;
import com.meli.desafio_quality.model.District;
import com.meli.desafio_quality.util.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import javax.swing.text.html.Option;
import java.util.Optional;

import static com.meli.desafio_quality.util.Util.allDistricts;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

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

    @Test
    void getDistrictByName() {
        District district = allDistricts().get(0);
        districtRepository.createDistrict(district);

        District foundDistrict = districtRepository.getDistrictByName(district.getDistrictName());
        Assertions.assertNotNull(foundDistrict.getDistrictName());
        Assertions.assertNotNull(foundDistrict.getValueDistrictM2());
        org.assertj.core.api.Assertions.assertThat(foundDistrict).isEqualTo(allDistricts().get(0));
    }

    @Test
    void NotFoundDistrictByName() {
        String name = "nome que não existe no BD";
        District response = null;
        Exception testException = null;
        try {
            response = districtRepository.getDistrictByName(name);
        } catch (Exception exception) {
            testException = exception;
        }
        Assertions.assertNull(response);
        org.assertj.core.api.Assertions.assertThat(testException.getMessage()).isEqualTo("Bairro não encontrado em nosso BD.");
    }

}