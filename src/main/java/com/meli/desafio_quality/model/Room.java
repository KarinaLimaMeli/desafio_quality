package com.meli.desafio_quality.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Room {
    private String roomName;
    private double width;
    private double length;
    private double roomTotalArea;

    //ver se precisa de constructor

    private void calculateTotalArea(double width, double length) {
        this.roomTotalArea = width * length;
    }
}
