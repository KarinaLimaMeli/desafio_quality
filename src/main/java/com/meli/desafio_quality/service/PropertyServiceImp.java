package com.meli.desafio_quality.service;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.Room;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PropertyServiceImp implements PropertyService{

    @Override
    public void createProperty(Property property) {

    }

    @Override
    public double calculateTotalArea(String name) {
        return 0;
    }

    @Override
    public BigDecimal calculateTotalPrice(String name) {
        return null;
    }

    @Override
    public Room biggestRoom(String name) {
        return null;
    }

    @Override
    public List<Room> getAreaByRoom(String name) {
        return null;
    }
}
