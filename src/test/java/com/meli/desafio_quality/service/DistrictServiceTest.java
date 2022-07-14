package com.meli.desafio_quality.service;

import com.meli.desafio_quality.mocks.DistrictMocks;
import com.meli.desafio_quality.model.District;
import com.meli.desafio_quality.repository.DistrictRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static com.meli.desafio_quality.util.Util.allDistricts;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class DistrictServiceTest {

    @InjectMocks
    DistrictServiceImp service;

    @Mock
    DistrictRepository districtRepository;

    @Test
    void getDistrictByName() {
        String name = allDistricts().get(0).getDistrictName();
        DistrictMocks.mock_getDistrictByName(name, districtRepository);

        District district = service.getDistrictByName(name);
        verify(districtRepository, atLeastOnce()).getDistrictByName(name);

        Assertions.assertNotNull(district.getDistrictName());
        Assertions.assertNotNull(district.getValueDistrictM2());
        assertThat(district).isEqualTo(allDistricts().get(0));
    }

    @Test
    void NotFoundDistrictByName() {
        String name = "nome que não existe no BD";
        DistrictMocks.mock_notFoundGetDistrictByName(districtRepository);
        District response = null;
        Exception testeException = null;
        try {
            response = service.getDistrictByName(name);
        } catch (Exception exception) {
            testeException = exception;
        }
        verify(districtRepository, atLeastOnce()).getDistrictByName(name);
        Assertions.assertNull(response);
        assertThat(testeException.getMessage()).isEqualTo("teste");
    }
}
