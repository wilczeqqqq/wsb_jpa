package com.jpacourse.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpacourse.dto.PatientTO;
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
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PatientServiceTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    @Test
    public void testShouldRemovePatientAndVisitsButNotDoctors() {
        //Given: Data before entity deletion
        final PatientEntity patientEntity = patientDao.findOne(1L);
        final VisitEntity visitEntity = patientEntity.getVisits().get(0);

        Long patientId = patientEntity.getId();
        Long visitId = visitEntity.getId();
        Long doctorId = visitEntity.getDoctor().getId();

        // When: Delete the patient by their ID
        patientService.deleteById(patientId);

        // Then: Data after entity deletion
        assertThat(patientDao.findOne(patientId)).isNull(); // Patient is removed
        assertThat(visitDao.findOne(visitId)).isNull();   // Visit is removed
        assertThat(doctorDao.findOne(doctorId)).isNotNull();   // Doctor applied to this visit isn't removed.
    }

    @Test
    public void testShouldGetPatientEqualToFormattedJsonObject() throws Exception
    {
        //Given: Fetching json file from resources folder and mapping to PatientTO.
        String expectedJson = new String(Files.readAllBytes(
                Paths.get(new ClassPathResource("patientGetById.json").getURI())));
        PatientTO expectedDto = objectMapper.readValue(expectedJson, PatientTO.class);

        //When: Get the patient by ID.
        PatientTO actualDto = patientService.findById(1L);

        //Then: String comparison.
        assertThat(actualDto).isEqualTo(expectedDto);
        assertThat(actualDto.isActive()).isEqualTo(expectedDto.isActive()); // For task purposes.
    }

}
