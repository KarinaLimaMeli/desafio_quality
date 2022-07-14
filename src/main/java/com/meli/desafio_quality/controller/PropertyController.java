package com.meli.desafio_quality.controller;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public double calculateTotalArea(@PathVariable String name) {
        return propertyService.calculateTotalArea(name);
    }

    @GetMapping("/totalprice/{name}")
    public double calculateTotalPrice(@PathVariable String name){
        return propertyService.calculateTotalPrice(name);
    }




    /*
    requisito 3
    - Determine qual é o maior cômodo.

    @GetMapping("/{name}/biggestRoom")
    requisito 4
    - Determinar a quantidade de metros quadrados que tem cada
cômodo de uma propriedade.

    @GetMapping("/name}/m2byroom")
     */

}
