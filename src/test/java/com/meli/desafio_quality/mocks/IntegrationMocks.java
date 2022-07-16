package com.meli.desafio_quality.mocks;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class IntegrationMocks {
    public static void mock_integration_createDistrictInDb(MockMvc mockMvc, String payLoadDistrict) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/district")
                .contentType(MediaType.APPLICATION_JSON).content(payLoadDistrict));
    }
}
