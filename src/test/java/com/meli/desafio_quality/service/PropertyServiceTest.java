package com.meli.desafio_quality.service;

import com.meli.desafio_quality.exception.DistrictNotFound;
import com.meli.desafio_quality.exception.PropertyNameNotFound;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.Room;
import com.meli.desafio_quality.repository.DistrictRepository;
import com.meli.desafio_quality.repository.PropertyRepository;
import com.meli.desafio_quality.util.UtilDistrict;
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

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@Log4j2
class PropertyServiceTest {

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
        BDDMockito.when(propertyRepository.findByName(ArgumentMatchers.anyString()))
                .thenReturn(UtilProperty.allProperies().get(0));
    }

    @Test
    @DisplayName("Valida o método creatProperty se for passado um distric válido")
    void createProperty() {
        Property newProperty = UtilProperty.allProperies().get(0);
        service.createProperty(newProperty);
        verify(propertyRepository, Mockito.atLeastOnce()).createProperty(newProperty);
    }

    @Test
    @DisplayName("Valida se retorna uma exception caso o nome passado como district não seja válido")
    void notCreateProperty() {
        Property newProperty = UtilProperty.propertyDistrictNull();
        try {
            service.createProperty(newProperty);
        } catch (DistrictNotFound districtNotFound) {
            assertEquals(districtNotFound.getMessage(), "Bairro não cadastrado!");
        }
    }

    @Test
    @DisplayName("Valida se retorna o valor total da área")
    void calculateTotalArea() {
        Property newProperty = UtilProperty.allProperies().get(0);
        service.createProperty(newProperty);
        String name = newProperty.getPropertyName();
        double result = service.calculateTotalArea(name);

        assertEquals(result, 160.0);
    }

    @Test
    @DisplayName("Valida se retorna uma exception quando o nome não é encontrado")
    void notFindByName() {
        BDDMockito.when(service.findByName(ArgumentMatchers.anyString())).thenAnswer(invocationOnMock -> {
            throw new Exception("teste");
        });
        try{
            Property property = service.findByName("casa");
            System.out.println(property);
        }catch (PropertyNameNotFound propertyNameNotFound) {
            assertEquals(propertyNameNotFound.getMessage(), "Este imóvel não foi encontrado!");
        }
    }

    @Test
    @DisplayName("Valida o retorno esperado no total price")
    void calculateTotalPrice() {
        Property newProperty = UtilProperty.allProperies().get(0);
        service.createProperty(newProperty);
        double totalPrice = service.calculateTotalPrice(newProperty.getPropertyName());
        assertEquals(totalPrice, 320000.0);
    }

    @Test
    @DisplayName("Valida o retorno do maior cômodo")
    void biggestRoom() {
        Property newProperty = UtilProperty.allProperies().get(0);
        service.createProperty(newProperty);
        Room room = service.biggestRoom(newProperty.getPropertyName());
        assertEquals(room.getRoomName(), "Quarto");
    }

    @Test
    @DisplayName("Valida o total área por cômodo")
    void getAreaByRooms() {
        Property newProperty = UtilProperty.allProperies().get(0);
        service.createProperty(newProperty);
        List<Room> rooms = service.getAreaByRooms(newProperty.getPropertyName());

        assertEquals(rooms.get(0).getRoomName(), "Sala");
        assertEquals(rooms.get(0).getTotalArea(), 50.0);
        assertEquals(rooms.get(1).getTotalArea(), 50.0);
        assertEquals(rooms.get(2).getTotalArea(), 60.0);
    }
}