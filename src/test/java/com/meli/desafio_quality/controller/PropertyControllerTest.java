package com.meli.desafio_quality.controller;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.Room;
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

import java.util.List;

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

        BDDMockito.when(propertyService.biggestRoom(ArgumentMatchers.anyString()))
            .thenReturn(UtilProperty.bigRoom());

        BDDMockito.when(propertyService.getAreaByRooms(ArgumentMatchers.anyString()))
            .thenReturn(UtilProperty.allRooms());

        BDDMockito.when(propertyService.findByName(ArgumentMatchers.anyString()))
            .thenReturn(UtilProperty.allProperies().get(0));

        BDDMockito.when(propertyService.calculateTotalPrice(ArgumentMatchers.anyString()))
            .thenReturn(100000.0);
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
    @DisplayName("Valida se retorna uma propriedade com todos os seus dados.")
    void findByName() {
        Property property = UtilProperty.allProperies().get(0);
        controller.createProperty(property);
        String name = property.getPropertyName();

        ResponseEntity<Property> response = controller.findByName(name);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getPropertyName()).isEqualTo(name);
        assertThat(response.getBody().getRoomList().get(0).getTotalArea()).isEqualTo(50.0);
    }

    @Test
    @DisplayName("Valida se retorna o valor correto do total da área da propriedade.")
    void calculateTotalArea() {
        Property property = UtilProperty.allProperies().get(0);
        controller.createProperty(property);

        String name = property.getPropertyName();
        ResponseEntity<Double> response = controller.calculateTotalArea(name);

        assertThat(response.getBody()).isPositive();
        assertThat(response.getBody()).isEqualTo(150.0);
    }

    @Test
    @DisplayName("Valida se retorna o valor total da propriedade.")
    void calculateTotalPrice() {
        Property property = UtilProperty.allProperies().get(0);
        controller.createProperty(property);

        String name = property.getPropertyName();

        ResponseEntity<Double> response = controller.calculateTotalPrice(name);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isEqualTo(100000.0);
    }

    @Test
    @DisplayName("Valida se retorna o maior cômodo.")
    void biggestRoom() {
        Property property = UtilProperty.allProperies().get(0);
        controller.createProperty(property);

        String name = property.getPropertyName();

        ResponseEntity<Room> response = controller.biggestRoom(name);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getRoomName()).isEqualTo("Quarto");
    }

    @Test
    @DisplayName("Valida se retorna os cômodos por área.")
    void getAreaByRooms() {
        Property property = UtilProperty.allProperies().get(0);
        controller.createProperty(property);

        String name = property.getPropertyName();

        ResponseEntity<List<Room>> response = controller.getAreaByRooms(name);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get(0).getTotalArea()).isNotNull();
        assertThat(response.getBody().get(0).getTotalArea()).isEqualTo(50.0);
    }
}