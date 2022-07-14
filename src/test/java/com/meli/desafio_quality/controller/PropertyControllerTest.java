package com.meli.desafio_quality.controller;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.service.PropertyService;
import com.meli.desafio_quality.util.UtilProperty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PropertyControllerTest {

    @InjectMocks
    private PropertyController controller;

    @Mock
    PropertyService propertyService;

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
    void findByName() {
    }

    @Test
    void calculateTotalArea() {
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