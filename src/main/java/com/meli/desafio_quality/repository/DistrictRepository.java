package com.meli.desafio_quality.repository;

import com.meli.desafio_quality.exception.DistrictAlreadyExist;
import com.meli.desafio_quality.exception.DistrictNotFound;
import com.meli.desafio_quality.model.District;
import lombok.Data;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Getter
public class DistrictRepository {
    private ArrayList<District> allDistricts;

    public DistrictRepository() { this.allDistricts = new ArrayList<>(); }

    public District createDistrict(District district) {
        if(getAllDistricts().contains(district)) { throw new DistrictAlreadyExist("Bairro já cadastrado"); }
        else {
            allDistricts.add(district);
            return district;
        }
    }

    public District getDistrictByName(String name) {
        return this.getAllDistricts().stream().filter(district -> district.getDistrictName().equalsIgnoreCase(name))
                .findFirst().orElseThrow(()-> { throw new DistrictNotFound("Bairro não encontrado em nosso BD."); } );
    }
}
