package com.reservation.reservationservice.interationTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.junit.Before;
import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static com.reservation.reservationservice.constants.Constants.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class UnavilabilityControllerIntegrationTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void addUnavilabilityTest() throws Exception {
        String json = json(NEW_UNAVILABILITY_DTO_1);
        mockMvc.perform(post("/api/unavilability/addUnavilability").content(json).contentType(contentType)).andExpect(status().isOk());
    }
    @Test
    public void addUnavilabilityBadTest() throws Exception {
        String json = json(NEW_UNAVILABILITY_DTO_NO_ACCOMODATION);
        mockMvc.perform(post("/api/unavilability/addUnavilability").content(json).contentType(contentType)).andExpect(status().isBadRequest());
    }
    @Test
    public void addUnavilabilityAlreadyExistest() throws Exception {
        String json = json(NEW_UNAVILABILITY_DTO_2);
        mockMvc.perform(post("/api/unavilability/addUnavilability").content(json).contentType(contentType)).andExpect(status().isOk());
        String json2 = json(NEW_UNAVILABILITY_DTO_3);
        mockMvc.perform(post("/api/unavilability/addUnavilability").content(json2).contentType(contentType)).andExpect(status().isBadRequest());


    }

    public static String json(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper.writeValueAsString(object);
    }
}
