package com.meli.desafio_quality.repository;

import com.meli.desafio_quality.model.District;

import java.util.ArrayList;

public interface DistrictRepository {

    void createDistrict(District district);

    ArrayList<District> getAllDistrics();

    District getDistrictByName(String name);
}
