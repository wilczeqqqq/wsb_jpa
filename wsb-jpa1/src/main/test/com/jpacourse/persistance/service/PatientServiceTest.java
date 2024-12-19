package com.jpacourse.persistance.service;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PatientServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    @Test
    public void testShouldRemovePatientAndVisitsButNotDoctors()
    {

        //Given
        final PatientEntity patientEntity = patientDao.findOne(1L); //Gets patient for given ID.
        long patientId = patientEntity.getId(); //Save ID of selected patient.

        List<VisitEntity> visits = patientEntity.getVisits(); // Get visits for given patient.

        VisitEntity visitEntity = visits.get(0); // Get for testing purposes first found visit.
        Long doctorId = visitEntity.getDoctor().getId(); // Get doctor ID assigned to this visit.
        Long visitId = visitEntity.getId();

        // When: Delete the patient by their ID
        patientDao.delete(patientEntity);

        // Then
        assertThat(patientDao.findOne(patientId)).isNull(); // Patient is removed
        assertThat(visitDao.findOne(visitId)).isNull();   // Visit is removed
        assertThat(doctorDao.findOne(doctorId)).isNotNull();   // Doctor applied to this visit isn't removed.

    }

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

