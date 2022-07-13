package com.meli.desafio_quality.service;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.Room;

import java.math.BigDecimal;
import java.util.List;

public interface PropertyService {
    private Property findByName(String name) {
        return null;
    }

    //req 0
    void createProperty(Property property);

    // req 1
    double calculateTotalArea(String name);

    // req 2
    BigDecimal calculateTotalPrice(String name);

    // req 3
    Room biggestRoom(String name);

    // req 4

    List<Room> getAreaByRoom(String name);
}
