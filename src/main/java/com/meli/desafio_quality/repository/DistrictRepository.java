package com.meli.desafio_quality.repository;

import com.meli.desafio_quality.model.District;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Repository
@Data
public class DistrictRepository {

    private ArrayList<District> allDistricts;

    public DistrictRepository() {
        this.allDistricts = new ArrayList<>();
    }

    public void createDistrict(District district) {
        this.allDistricts.add(district);
    }

    public ArrayList<District> getAllDistrics() {
        return this.allDistricts;
    }

    public District getDistrictByName(String name) {
        return this.getAllDistrics().stream().filter(district -> district.getDistrictName().equals(name))
                .collect(Collectors.toList()).get(0);
    }
}
