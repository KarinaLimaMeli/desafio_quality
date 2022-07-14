package com.meli.desafio_quality.controller;
import com.meli.desafio_quality.mocks.DistrictMocks;
import com.meli.desafio_quality.model.District;
import com.meli.desafio_quality.service.DistrictService;
import com.meli.desafio_quality.util.Util;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.stream.Collectors;

import static com.meli.desafio_quality.util.Util.allDistricts;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DistrictControllerTest {

        @InjectMocks
        private DistrictController controller;

        @Mock
        DistrictService districtService;

//    @BeforeEach
//    void setUp() {
//
//    }
//
//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void createDistrict() {
        District newDistrict = allDistricts().get(0);
        DistrictMocks.mock_createDistrict(newDistrict, districtService);

        ResponseEntity<Void> response = controller.createDistrict(newDistrict);
        verify(districtService, atLeastOnce()).createDistrict(newDistrict);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void districtAlreadyExist() {
        District newDistrict = allDistricts().get(0);
        DistrictMocks.mock_districtAlreadyExist(newDistrict, districtService);
        Exception testException = null;
        try {
            controller.createDistrict(newDistrict);
        } catch (Exception exception) {
            testException = exception;
        }
        verify(districtService, atLeastOnce()).createDistrict(newDistrict);
        assertThat(testException.getMessage()).isEqualTo("teste");

    }

    @Test
    void getDistrictByName() {
        String name = allDistricts().get(0).getDistrictName();
        DistrictMocks.mock_getDistrictByName(name, districtService);

        ResponseEntity<District> response = controller.getDistrictByName(name);
        verify(districtService, atLeastOnce()).getDistrictByName(name);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertTrue(response.getBody().getDistrictName().equals(name));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void NotFoundDistrictByName() {
        String name = "nome que não existe no BD";
        DistrictMocks.mock_notFoundGetDistrictByName(districtService);
        ResponseEntity<District> response = null;
        Exception testeException = null;
        try {
            response = controller.getDistrictByName(name);
        } catch (Exception exception) {
            testeException = exception;
        }
        verify(districtService, atLeastOnce()).getDistrictByName(name);
        Assertions.assertNull(response);
        assertThat(testeException.getMessage()).isEqualTo("teste");
    }

}