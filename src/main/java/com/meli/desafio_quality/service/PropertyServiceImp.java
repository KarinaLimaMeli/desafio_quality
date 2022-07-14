package com.meli.desafio_quality.service;

import com.meli.desafio_quality.exception.PropertyNameNotFound;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.Room;
import com.meli.desafio_quality.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
public class PropertyServiceImp implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public void createProperty(Property property) {
        propertyRepository.createProperty(property);
    }

    @Override
    public double calculateTotalArea(String name) {
        Property property = findByName(name);
        return property.getRoomList().stream().map(r -> r.getLength() * r.getWidth())
                .reduce(Double::sum).orElse(0.0);
    }

    @Override
    public Property findByName(String name) {
        try {
            return propertyRepository.findByName(name);
        } catch (Exception ex) {
            throw new PropertyNameNotFound("Este imóvel não foi encontrado!");
        }
    }

    @Override
    public double calculateTotalPrice(String name) {
        Property property = findByName(name);
        BigDecimal valueDistrict = property.getDistrict().getValueDistrictM2();
        double totalArea = calculateTotalArea(name);
        return valueDistrict.doubleValue() * totalArea;
    }

    @Override
    public Room biggestRoom(String name) {
        Property property = findByName(name);
        return property.getRoomList().stream()
                .max(Comparator.comparing(r -> r.getLength() * r.getWidth())).orElseThrow();
    }


    @Override
    public List<Room> getAreaByRooms(String name) {
        Property property = findByName(name);
        property.getRoomList()
                .forEach(room -> room.setTotalArea(room.getLength() * room.getWidth()));
        return property.getRoomList();

    }
}
