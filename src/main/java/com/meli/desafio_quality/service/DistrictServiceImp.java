package com.meli.desafio_quality.service;

import com.meli.desafio_quality.model.District;
import com.meli.desafio_quality.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImp implements DistrictService {

    @Autowired
    DistrictRepository districtRepository;

    @Override
    public void createDistrict(District district) {

    }

    @Override
    public District getDistrictByName(String name) {
        try {
            return districtRepository.getDistrictByName(name);
        } catch (Exception e) {
            throw e;
        }

    }
}
