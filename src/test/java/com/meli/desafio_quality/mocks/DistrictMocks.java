package com.meli.desafio_quality.mocks;
import com.meli.desafio_quality.repository.DistrictRepository;
import com.meli.desafio_quality.service.DistrictService;
import com.meli.desafio_quality.model.District;
import com.meli.desafio_quality.util.UtilDistrict;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class DistrictMocks {
    public static void mock_getDistrictByName(String name, DistrictService service) {
        District foundDistrict = UtilDistrict.allDistricts().stream().filter(
                district -> district.getDistrictName().equals(name)).findFirst().get();
        BDDMockito.when(service.getDistrictByName(ArgumentMatchers.anyString()))
                .thenReturn(foundDistrict);
    }

    public static void mock_getDistrictByName(String name, DistrictRepository repository) {
        District foundDistrict = UtilDistrict.allDistricts().stream().filter(
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

    public static void mock_createDistrict(DistrictService service, District district) {
        BDDMockito.when(service.createDistrict(ArgumentMatchers.any(District.class))).
                thenReturn(district);
    }

    public static void mock_createDistrict(DistrictRepository repository, District district) {
        BDDMockito.when(repository.createDistrict(ArgumentMatchers.any(District.class))).
                thenReturn(district);
    }

    public static void mock_districtAlreadyExist(DistrictService service) {
        BDDMockito.when(service.createDistrict(ArgumentMatchers.any(District.class)))
                .thenAnswer(invocationOnMock -> {
                    throw new Exception("teste");
                });
    }

    public static void mock_districtAlreadyExist(DistrictRepository repository) {
        BDDMockito.when(repository.createDistrict(ArgumentMatchers.any(District.class)))
                .thenAnswer(invocationOnMock -> {
                    throw new Exception("teste");
                });
    }

    public static void mock_integration_createDistrictInDb(MockMvc mockMvc, String payLoadDistrict) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/district")
                .contentType(MediaType.APPLICATION_JSON).content(payLoadDistrict));
    }
}
