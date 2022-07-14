package com.meli.desafio_quality.repository;

import com.meli.desafio_quality.exception.DistrictAlreadyExist;
import com.meli.desafio_quality.exception.DistrictNotFound;
import com.meli.desafio_quality.model.District;
import lombok.Data;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Data
public class DistrictRepository {
    private ArrayList<District> allDistricts;

    public DistrictRepository() { this.allDistricts = new ArrayList<>(); }

    public void createDistrict(District district) {
        Optional<District> teste = getAllDistricts().stream().filter(eachDistrict -> eachDistrict.getDistrictName()
                .equalsIgnoreCase(district.getDistrictName())).findFirst();
        if(teste.isEmpty()) { allDistricts.add(district); }
        else { throw new DistrictAlreadyExist("Bairro já cadastrado"); }
    }

    public ArrayList<District> getAllDistricts() {
        return this.allDistricts;
    }

    public District getDistrictByName(String name) {
        return this.getAllDistricts().stream().filter(district -> district.getDistrictName().equalsIgnoreCase(name))
                .findFirst().orElseThrow(()-> { throw new DistrictNotFound("Bairro não encontrado em nosso BD."); } );
    }
}
