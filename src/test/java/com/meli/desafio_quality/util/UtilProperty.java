package com.meli.desafio_quality.util;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.Room;

import java.util.ArrayList;

public class UtilProperty {

    public static Property propertyDistrictNull() {
        return new Property("Apartamento01", null, allRooms());
    }

    public static ArrayList<Property> allProperies() {
        ArrayList<Property> properties = new ArrayList<>();

        properties.add(new Property(
            "Apartamento01",
            UtilDistrict.allDistricts().get(0),
            allRooms()));
        return properties;
    }

    public static ArrayList<Room> allRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Sala", 10.0, 5.0, 0.0));
        rooms.add(new Room("Cozinha", 10.0, 5.0, 0.0));
        rooms.add(new Room("Quarto", 10.0, 5.0, 0.0));
        return rooms;
    }

    public static Room bigRoom() {
        return new Room("Quarto", 10.0, 5.0, 0.0);
    }
}
