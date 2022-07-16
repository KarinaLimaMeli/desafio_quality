package com.meli.desafio_quality.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.desafio_quality.controller.DistrictController;
import com.meli.desafio_quality.handlerException.HandlerDistrictExeptions;
import com.meli.desafio_quality.mocks.DistrictMocks;
import com.meli.desafio_quality.mocks.IntegrationMocks;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.util.UtilDistrict;
import com.meli.desafio_quality.util.UtilProperty;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PropertyCreate {

    @LocalServerPort
    private int PORT;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void createProperty() throws Exception {
        Property property = UtilProperty.allProperies().get(0);

        String baseURL = "http://localhost:" + PORT + "/property/create";

        String payLoadDistrict = UtilDistrict.toJson(UtilDistrict.allDistricts().get(0));

        IntegrationMocks.mock_integration_createDistrictInDb(mockMvc, payLoadDistrict);

        HttpEntity<Property> httpEntity = new HttpEntity<>(property);

        ResponseEntity<Void> result = testRestTemplate.exchange(
                baseURL,
                HttpMethod.POST,
                httpEntity,
                Void.class
        );

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isNull();
    }

    @Test
    public void if_createProperty_throws_exception_DistrictNotFound() {
        Property property = UtilProperty.allProperies().get(0);

        String baseURL = "http://localhost:" + PORT + "/property/create";

        HttpEntity<Property> httpEntity = new HttpEntity<>(property);

        ResponseEntity<Void> result = testRestTemplate.exchange(
                baseURL,
                HttpMethod.POST,
                httpEntity,
                Void.class
        );
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}

