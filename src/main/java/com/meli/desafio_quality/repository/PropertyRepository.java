package com.meli.desafio_quality.repository;

import com.meli.desafio_quality.model.Property;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Repository
public class PropertyRepository {

    ArrayList<Property> properties = new ArrayList<Property>();

    public void createProperty(Property property) {
        properties.add(property);
    }

    public Property findByName(String name) {
        Property x = properties.stream().filter(property -> property.getPropertyName()
                .equals(name)).collect(Collectors.toList()).get(0);
        return x;
    }
}
