package com.meli.desafio_quality.mocks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.meli.desafio_quality.model.Property;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


public class IntegrationMocks {

    public static void mock_integration_createPropertyInDb(MockMvc mockMvc, String payLoadProperty) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/property/create")
                .contentType(MediaType.APPLICATION_JSON).content(payLoadProperty));
    }

    public static String toJson (Property property) throws JsonProcessingException {
        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();
        return writer.writeValueAsString(property);
    }

    public static void mock_integration_createDistrictInDb(MockMvc mockMvc, String payLoadDistrict) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/district")
                .contentType(MediaType.APPLICATION_JSON).content(payLoadDistrict));
    }


}
