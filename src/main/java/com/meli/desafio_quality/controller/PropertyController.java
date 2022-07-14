package com.meli.desafio_quality.controller;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.Room;
import com.meli.desafio_quality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/create")
    public ResponseEntity<Void> createProperty(@RequestBody @Valid Property property) {
        propertyService.createProperty(property);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Property> findByName(@PathVariable String name) {
        return ResponseEntity.ok().body(propertyService.findByName(name));
    }

    @GetMapping("/totalm2/{name}")
    public ResponseEntity<Double> calculateTotalArea(@PathVariable String name) {
        return ResponseEntity.ok().body(propertyService.calculateTotalArea(name));
    }

    @GetMapping("/totalprice/{name}")
    public ResponseEntity<Double> calculateTotalPrice(@PathVariable String name) {
        return ResponseEntity.ok().body(propertyService.calculateTotalPrice(name));
    }

    @GetMapping("/biggestroom/{name}")
    public ResponseEntity<Room> biggestRoom(@PathVariable String name) {
        return ResponseEntity.ok().body(propertyService.biggestRoom(name));
    }

    @GetMapping("/m2byrooms/{name}")
    public ResponseEntity<List<Room>> getAreaByRooms(@PathVariable String name) {
        return ResponseEntity.ok().body(propertyService.getAreaByRooms(name));
    }
}
