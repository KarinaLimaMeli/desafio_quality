package com.meli.desafio_quality.model;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    private String roomName;
    private double width;
    private double length;
    private double totalArea;

}
