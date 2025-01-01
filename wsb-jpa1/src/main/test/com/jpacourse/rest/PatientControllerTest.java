package com.jpacourse.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testControllerResponseMatchesJsonFile() throws Exception
    {
        //Given: Fetching json file from resources folder.
        String expectedJson = new String(Files.readAllBytes(
                Paths.get(new ClassPathResource("patientGetById.json").getURI())));

        //When: Mock endpoint call.
        String actualJson = mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //Then: String comparison.
        assertThat(actualJson).isEqualToIgnoringWhitespace(expectedJson);
    }

}
