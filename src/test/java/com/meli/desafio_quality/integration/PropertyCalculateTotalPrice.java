package com.meli.desafio_quality.integration;

import com.meli.desafio_quality.mocks.IntegrationMocks;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.util.UtilDistrict;
import com.meli.desafio_quality.util.UtilProperty;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PropertyCalculateTotalPrice {

    @LocalServerPort
    private int PORT;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void totalprice() throws Exception {
        String payLoadDistrict = UtilDistrict.toJson(UtilDistrict.allDistricts().get(0));
        IntegrationMocks.mock_integration_createDistrictInDb(mockMvc, payLoadDistrict);

        Property property = UtilProperty.allProperies().get(0);
        IntegrationMocks.mock_integration_createPropertyInDb(mockMvc, IntegrationMocks.toJson(property));

        String name = UtilProperty.allProperies().get(0).getPropertyName();
        String baseURL = "http://localhost:" + PORT + "/property/totalprice/" + name;

        ResponseEntity<Double> result = testRestTemplate.exchange(
                baseURL,
                HttpMethod.GET,
                null,
                Double.class
        );

        assertThat(result.getBody()).isNotNull();
    }
}
