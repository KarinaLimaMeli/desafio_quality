package com.meli.desafio_quality.mocks;
import com.meli.desafio_quality.repository.DistrictRepository;
import com.meli.desafio_quality.service.DistrictService;
import com.meli.desafio_quality.model.District;
import com.meli.desafio_quality.util.Util;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;

import java.util.stream.Collectors;

public class DistrictMocks {
    public static void mock_getDistrictByName(String name, DistrictService service) {
        District foundDistrict = Util.allDistricts().stream().filter(
                district -> district.getDistrictName().equals(name)).findFirst().get();
        BDDMockito.when(service.getDistrictByName(ArgumentMatchers.anyString()))
                .thenReturn(foundDistrict);
    }

    public static void mock_getDistrictByName(String name, DistrictRepository repository) {
        District foundDistrict = Util.allDistricts().stream().filter(
                district -> district.getDistrictName().equals(name)).findFirst().get();
        BDDMockito.when(repository.getDistrictByName(ArgumentMatchers.anyString()))
                .thenReturn(foundDistrict);
    }

    public static void mock_notFoundGetDistrictByName(DistrictService service) {
        BDDMockito.when(service.getDistrictByName(ArgumentMatchers.anyString()))
                .thenAnswer(invocationOnMock -> {
                    throw new Exception("teste");
                });
    }

    public static void mock_notFoundGetDistrictByName(DistrictRepository repository) {
        BDDMockito.when(repository.getDistrictByName(ArgumentMatchers.anyString()))
                .thenAnswer(invocationOnMock -> {
                    throw new Exception("teste");
                });
    }
}