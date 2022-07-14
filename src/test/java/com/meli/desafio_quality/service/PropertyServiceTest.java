package com.meli.desafio_quality.service;

import com.meli.desafio_quality.exception.DistrictNotFound;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.repository.DistrictRepository;
import com.meli.desafio_quality.repository.PropertyRepository;
import com.meli.desafio_quality.util.UtilDistrict;
import com.meli.desafio_quality.util.UtilProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PropertyServiceTest {

    @InjectMocks
    PropertyServiceImp propertyServiceImp;

    @Mock
    DistrictRepository districtRepository;

    @Mock
    PropertyRepository propertyRepository;

    @BeforeEach
    public void setup() {
        BDDMockito.doNothing().when(propertyRepository).createProperty(ArgumentMatchers.any(Property.class));
        BDDMockito.when(districtRepository.getDistrictByName(ArgumentMatchers.anyString())).
                thenReturn(UtilDistrict.allDistricts().get(0));
        BDDMockito.when(propertyServiceImp.calculateTotalArea(ArgumentMatchers.anyString()))
                .thenReturn(ArgumentMatchers.anyDouble());
        BDDMockito.when(propertyRepository.findByName(ArgumentMatchers.anyString()))
                .thenReturn(ArgumentMatchers.any(Property.class));
    }

    @Test
    void createProperty() {
        Property newProperty = UtilProperty.getOneProperty();
        propertyServiceImp.createProperty(newProperty);
        verify(propertyRepository, atLeastOnce()).createProperty(newProperty);
    }

    @Test
    void notCreateProperty() {
        Property newProperty = UtilProperty.propertyDistrictNull();
        try {
            propertyServiceImp.createProperty(newProperty);
        } catch (DistrictNotFound districtNotFound) {
            assertEquals(districtNotFound.getMessage(), "Bairro não cadastrado!");
        }
    }

    @Test
    void calculateTotalArea() {
        Property newProperty = UtilProperty.getOneProperty();
        propertyServiceImp.createProperty(newProperty);
        Double response = propertyServiceImp.calculateTotalArea(newProperty.getPropertyName());
        //assertThat(response).isEqualTo(150.0);
    }

    @Test
    void findByName() {
        Property property = UtilProperty.getOneProperty();
        Property propertyWithName = propertyServiceImp.findByName(property.getPropertyName());
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