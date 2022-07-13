package com.meli.desafio_quality.controller;

import com.meli.desafio_quality.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/property")
public class PropertyController {

//    @Autowired
//    PropertyService propertyService;


    /*
    requisito 0
    @Post
    cadastra propriedade


    requisito 1
    - Calcule o total de metros quadrados de uma propriedade
  @GetMapping("/{name}/totalm2")

    requisito 2
    - Indique o valor de uma propriedade com base em seus cômodos e
medidas. Lembre-se que os preços por metro quadrado são determinados de
acordo com a vizinhança.

@GetMapping("/{name}/totalprice)

    requisito 3
    - Determine qual é o maior cômodo.

    @GetMapping("/{name}/biggestRoom")

    requisito 4
    - Determinar a quantidade de metros quadrados que tem cada
cômodo de uma propriedade.

    @GetMapping("/name}/m2byroom")


     */

}
