package com.meli.desafio_quality.service;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.Room;

import java.util.List;

public interface PropertyService {

    void createProperty(Property property);

    double calculateTotalArea(String name);

    Property findByName(String name);

    double calculateTotalPrice(String name);

    // req 3
    Room biggestRoom(String name);

    // req 4

    List<Room> getAreaByRoom(String name);

}
