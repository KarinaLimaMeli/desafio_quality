package com.meli.desafio_quality.service;

import com.meli.desafio_quality.exception.DistrictNotFound;
import com.meli.desafio_quality.model.District;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.repository.DistrictRepository;
import com.meli.desafio_quality.repository.PropertyRepository;
import com.meli.desafio_quality.util.UtilDistrict;
import com.meli.desafio_quality.util.UtilProperty;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Log4j2
class PropertyServiceImpTest {

    @InjectMocks
    PropertyServiceImp service;

    @Mock
    PropertyRepository propertyRepository;

    @Mock
    DistrictRepository districtRepository;

    @BeforeEach
    public void setup() {
        BDDMockito.doNothing().when(propertyRepository).createProperty(ArgumentMatchers.any(Property.class));

        BDDMockito.when(districtRepository.getDistrictByName(ArgumentMatchers.anyString()))
            .thenReturn(UtilDistrict.allDistricts().get(0));
    }

    @Test
    void createProperty() {
        Property newProperty = UtilProperty.allProperies().get(0);
        service.createProperty(newProperty);

        verify(propertyRepository, Mockito.atLeastOnce()).createProperty(newProperty);
    }

    @Test
    void notCreateProperty() {
        Property newProperty = UtilProperty.propertyDistrictNull();
        try {
            service.createProperty(newProperty);
        } catch (DistrictNotFound districtNotFound) {
            assertEquals(districtNotFound.getMessage(), "Bairro não cadastrado!");
        }
    }

    @Test
    void calculateTotalArea() {
    }

    @Test
    void findByName() {
    }

    @Test
    void calculateTotalPrice() {
    }

    @Test
    void biggestRoom() {
    }

    @Test
    void getAreaByRooms() {
    }
}