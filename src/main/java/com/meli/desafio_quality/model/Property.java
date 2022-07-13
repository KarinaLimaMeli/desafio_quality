package com.meli.desafio_quality.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Property {
    private String propertyName;
    private District district;
    private List<Room> roomList;
    private double totalArea;

    // ver constructor
    private void setTotalArea() {
        this.roomList.stream().forEach(room -> {
            this.totalArea += room.getRoomTotalArea();
        });
    }
}
