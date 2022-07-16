package com.meli.desafio_quality.integration;

import com.meli.desafio_quality.model.District;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static com.meli.desafio_quality.util.UtilDistrict.allDistricts;
@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DistrictIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;

    @Test
    public void createDistrict_whenNewDistrict() {
        District newDistrict = allDistricts().get(0);
        HttpEntity<District> httpEntity = new HttpEntity<>(newDistrict);
        String baseUrl = "http://localhost:" + port + "/district";
        ResponseEntity<District> response = testRestTemplate.exchange(baseUrl, HttpMethod.POST, httpEntity, District.class);
        District districtBody = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertEquals(newDistrict.getDistrictName(), districtBody.getDistrictName());
        Assertions.assertEquals(newDistrict.getValueDistrictM2(), districtBody.getValueDistrictM2());
    }

    @Test
    public void createDistrict_returnStatusBadRequest_whenDistrictAlreadyExists() {
        District newDistrict = allDistricts().get(1);
        HttpEntity<District> httpEntity = new HttpEntity<>(newDistrict);
        String baseUrl = "http://localhost:" + port + "/district";
        testRestTemplate.exchange(baseUrl, HttpMethod.POST, httpEntity, District.class);
        ResponseEntity<District> response = testRestTemplate.exchange(baseUrl, HttpMethod.POST, httpEntity, District.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void createDistrict_returnsStatusBadRequest_whenInvalidName() {
        District newDistrict = new District("nome muito mas muito longo com mais de 45 caracteres", new BigDecimal(2000));
        HttpEntity<District> httpEntity = new HttpEntity<>(newDistrict);
        String baseUrl = "http://localhost:" + port + "/district";
        testRestTemplate.exchange(baseUrl, HttpMethod.POST, httpEntity, District.class);
        ResponseEntity<District> response = testRestTemplate.exchange(baseUrl, HttpMethod.POST, httpEntity, District.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void getDistrictByName_whenDistrictExists() {
        District district = allDistricts().get(3);
        String baseUrl = "http://localhost:" + port + "/district/";
        HttpEntity<District> httpEntity = new HttpEntity<>(district);
        testRestTemplate.exchange(baseUrl, HttpMethod.POST, httpEntity, District.class);
        ResponseEntity<District> response = testRestTemplate.exchange(
                baseUrl + district.getDistrictName(), HttpMethod.GET, null, District.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response).isNotNull();
        Assertions.assertEquals(response.getBody().getDistrictName(), district.getDistrictName());
        Assertions.assertEquals(response.getBody().getValueDistrictM2(), district.getValueDistrictM2());
    }

    @Test
    public void getDistrictByName_returnsNotFound_whenDistrictDoesNotExists() {
        String name = "um nome";
        String baseUrl = "http://localhost:" + port + "/district/";
        ResponseEntity<District> response = testRestTemplate.exchange(baseUrl + name, HttpMethod.GET, null, District.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
