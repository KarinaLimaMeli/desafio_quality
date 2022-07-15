package com.meli.desafio_quality.service;

import com.meli.desafio_quality.model.District;

public interface DistrictService {
    District createDistrict(District district);
    District getDistrictByName(String name);
}
