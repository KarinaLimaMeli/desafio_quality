package com.meli.desafio_quality.controller;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.service.PropertyService;
import com.meli.desafio_quality.util.UtilProperty;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Log4j2
class PropertyControllerTest {

    @InjectMocks
    private PropertyController controller;

    @Mock
    PropertyService propertyService;

    @BeforeEach
    public void setup() {
        BDDMockito.doNothing().when(propertyService).createProperty(ArgumentMatchers.any(Property.class));

        BDDMockito.when(propertyService.calculateTotalArea(ArgumentMatchers.anyString()))
            .thenReturn(150.0);

//        BDDMockito.when(propertyService.findByName(ArgumentMatchers.anyString()))
//            .thenReturn(UtilProperty.allProperies().get(0));
    }

    @Test
    @DisplayName("Valida se retorna o status 201 quando são informados os dados corretos de uma propriedade.")
    void createProperty_returnHttpCreated_whenNewProperty() {
        Property property = UtilProperty.allProperies().get(0);

        ResponseEntity<Void> response = controller.createProperty(property);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNull();

        Mockito.verify(propertyService, Mockito.atLeastOnce()).createProperty(property);
    }

    @Test
    @DisplayName("Valida se retorna o status 400 quando não são informados os dados corretos de uma propriedade.")
    void createProperty_returnHttpCBadRequest_whenNewPropertyWithoutDistrict() {
        // Property property = UtilProperty.propertyDistrictNull();
        Property property = new Property();
        property.setPropertyName("propertyName");
        property.setRoomList(UtilProperty.allRooms());
        ResponseEntity<Void> response = controller.createProperty(property);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNull();

        Mockito.verify(propertyService, Mockito.atLeastOnce()).createProperty(property);
    }

    @Test
    void findByName() {
    }

    @Test
    @DisplayName("Valida se retorna o valor correto do total da área da propriedade.")
    void calculateTotalArea() {
        // CRIA O PROPRIEDADE
        Property property = UtilProperty.allProperies().get(0);
        // log.info(property);
        ResponseEntity<Void> result = controller.createProperty(property);

        String name = property.getPropertyName();
        log.info(name);
        ResponseEntity<Double> response = controller.calculateTotalArea(name);

        assertThat(response.getBody()).isPositive();
        assertThat(response.getBody()).isEqualTo(150.0);
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