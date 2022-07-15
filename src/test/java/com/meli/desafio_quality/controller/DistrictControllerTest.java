package com.meli.desafio_quality.controller;
import com.meli.desafio_quality.mocks.DistrictMocks;
import com.meli.desafio_quality.model.District;
import com.meli.desafio_quality.service.DistrictService;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.meli.desafio_quality.util.UtilDistrict.allDistricts;
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

    @Test
    void createDistrict() {
        District newDistrict = allDistricts().get(0);
        DistrictMocks.mock_createDistrict(districtService, newDistrict);

        ResponseEntity<District> response = controller.createDistrict(newDistrict);

        verify(districtService, atLeastOnce()).createDistrict(newDistrict);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertEquals(response.getBody().getDistrictName(), newDistrict.getDistrictName());
        Assertions.assertEquals(response.getBody().getValueDistrictM2(), newDistrict.getValueDistrictM2());
    }

    @Test
    void districtAlreadyExist() {
        District newDistrict = allDistricts().get(0);
        DistrictMocks.mock_districtAlreadyExist(districtService);
        Exception testException = null;
        ResponseEntity<District> response = null;
        try {
            response = controller.createDistrict(newDistrict);
        } catch (Exception exception) {
            testException = exception;
        }
        verify(districtService, atLeastOnce()).createDistrict(newDistrict);
        Assertions.assertNull(response);
        assertThat(testException.getMessage()).isEqualTo("teste");
    }

    @Test
    void getDistrictByName() {
        String name = allDistricts().get(0).getDistrictName();
        DistrictMocks.mock_getDistrictByName(name, districtService);

        ResponseEntity<District> response = controller.getDistrictByName(name);
        verify(districtService, atLeastOnce()).getDistrictByName(name);

        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(response.getBody().getDistrictName(), name);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void NotFoundDistrictByName() {
        String name = "nome que não existe no BD";
        DistrictMocks.mock_notFoundGetDistrictByName(districtService);
        ResponseEntity<District> response = null;
        Exception testException = null;
        try {
            response = controller.getDistrictByName(name);
        } catch (Exception exception) {
            testException = exception;
        }
        verify(districtService, atLeastOnce()).getDistrictByName(name);
        Assertions.assertNull(response);
        assertThat(testException.getMessage()).isEqualTo("teste");
    }

}