package com.meli.desafio_quality.integration;

import com.meli.desafio_quality.model.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static com.meli.desafio_quality.util.UtilDistrict.allDistricts;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DistrictIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;

    @Test
    public void createDistrict_whenNewStudent() {
        District newDistrict = allDistricts().get(0);
        String baseUrl = "http://localhost:" + port + "/district";
        HttpEntity<District> httpEntity = new HttpEntity<>(newDistrict);
        ResponseEntity<District> response = testRestTemplate.exchange(baseUrl, HttpMethod.POST, httpEntity, District.class);
        District districtBody = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
