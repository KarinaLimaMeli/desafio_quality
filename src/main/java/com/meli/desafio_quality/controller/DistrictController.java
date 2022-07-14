package com.meli.desafio_quality.controller;

import com.meli.desafio_quality.model.District;
import com.meli.desafio_quality.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    private DistrictService districtService;
//  ->  /district
    @PostMapping()
    public ResponseEntity<Void> createDistrict(@RequestBody @Valid District district) {
        this.districtService.createDistrict(district);
        return new ResponseEntity(HttpStatus.CREATED);
    }

//  ->  /district/{name}
    @GetMapping("/{name}")
    public ResponseEntity<District> getDistrictByName(@PathVariable String districtName) {
        return ResponseEntity.ok(districtService.getDistrictByName(districtName));
    }
}
