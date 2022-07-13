package com.meli.desafio_quality.controller;
import com.meli.desafio_quality.model.District;
import com.meli.desafio_quality.service.DistrictService;
import com.meli.desafio_quality.util.Util;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DistrictControllerTest {

        @InjectMocks
        private DistrictController controller;

        @Mock
        DistrictService districtService;

    @BeforeEach
    void setUp() {
        BDDMockito.when(districtService.getDistrictByName(ArgumentMatchers.anyString()))
                .thenReturn(Util.allDistricts().get(0));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createDistrict() {
        District newDistrict = Util.allDistricts().get(0);
        ResponseEntity<Void> response = controller.createDistrict(newDistrict);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        verify(districtService, atLeastOnce()).createDistrict(newDistrict);
    }

    @Test
    void getDistrictByName() {
        String name = Util.allDistricts().get(0).getDistrictName();
        ResponseEntity<District> response = controller.getDistrictByName(name);
        verify(districtService, atLeastOnce()).getDistrictByName(name);

    }

    private void mock_getDistrictByName(String name) {
        District findedDistrict =
        BDDMockito.when(districtService.getDistrictByName(ArgumentMatchers.anyString()))
                .thenReturn(Util.allDistricts().get(0));

    }

    private void mock_createDistrict() {
        BDDMockito.doNothing().when(districtService).createDistrict(ArgumentMatchers.anyLong());
    }
}