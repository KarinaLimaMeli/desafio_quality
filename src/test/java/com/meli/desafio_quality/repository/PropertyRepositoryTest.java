package com.meli.desafio_quality.repository;

import com.meli.desafio_quality.model.Property;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.meli.desafio_quality.util.UtilProperty.allProperies;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PropertyRepositoryTest {

    private PropertyRepository propertyRepository;

    @BeforeEach
    void setup() { propertyRepository = new PropertyRepository(); }

    @Test
    void createProperty_whenNewProperty() {
        Property newProperty = allProperies().get(0);

        propertyRepository.createProperty(newProperty);
        Property foundProperty = propertyRepository.findByName("Apartamento01");

        assertThat(foundProperty).isNotNull();
        Assertions.assertEquals(foundProperty.getPropertyName(), newProperty.getPropertyName());
    }

    @Test
    void getPropertyByName() {
        Property property = allProperies().get(0);
        propertyRepository.createProperty(property);

        Property foundProperty = propertyRepository.findByName(property.getPropertyName());
        Assertions.assertNotNull(foundProperty.getPropertyName());
        Assertions.assertNotNull(foundProperty.getRoomList());
        org.assertj.core.api.Assertions.assertThat(foundProperty).isEqualTo(allProperies().get(0));
    }
}
