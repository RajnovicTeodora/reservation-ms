package com.reservation.reservationservice;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.junit.Before;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.containers.GenericContainer;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@Testcontainers
public class RequestIntegrationTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Container
    private static final GenericContainer<?> mongoContainer =
            new GenericContainer<>("mongo:latest")
                    .withExposedPorts(27017);

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void approveRequestOkTest() throws Exception {
        mockMvc.perform(put("/api/request/approveRequest/649edec1333ea471aa2a7deb").contentType(contentType)).andExpect(status().isOk());
    }
    @Test
    public void declineRequestOkTest() throws Exception {
        mockMvc.perform(put("/api/request/declineRequest/6491a35073e8a72c627af3f8").contentType(contentType)).andExpect(status().isOk());
    }
    @Test
    public void declineRequestBadTest() throws Exception {
        mockMvc.perform(put("/api/request/declineRequest/nonExistingId").contentType(contentType)).andExpect(status().isBadRequest());
    }
    @Test
    public void getRequestsByAccomodationOkTest() throws Exception {
        mockMvc.perform(get("/api/request/getRequestsByAccomodationId/648ebee63073a2143b4f95bf").contentType(contentType))
                .andExpect(status().isOk());
    }
    @Test
    public void getRequestsByAccomodationBadTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/request/getRequestsByAccomodationId/nonExistingId").contentType(contentType))
                .andExpect(status().isOk()).andReturn();
        String responseContent = result.getResponse().getContentAsString();
        assertTrue(responseContent.equals("[]"));
    }

    public static String json(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper.writeValueAsString(object);
    }
}
