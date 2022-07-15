package com.meli.desafio_quality.integration;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.util.UtilProperty;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PropertyCreate {

    @LocalServerPort
    private int PORT;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void createProperty() {
        Property property = UtilProperty.allProperies().get(0);

        String baseURL = "http://localhost:" + PORT + "/property/create";

        HttpEntity<Property> httpEntity = new HttpEntity<>(property);

        ResponseEntity<Property> result = testRestTemplate.exchange(
            baseURL,
            HttpMethod.POST,
            httpEntity,
            Property.class
        );

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
