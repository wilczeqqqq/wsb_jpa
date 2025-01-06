package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private DoctorDao doctorDao;

    @Autowired
    private VisitDao visitDao;

    @Test
    @Transactional
    public void testAddVisitToPatient(){

        // Given
        PatientEntity patient = patientDao.findOne(3L);
        DoctorEntity doctor = doctorDao.findOne(1L);
        LocalDateTime visitDate = LocalDateTime.now();
        String description = "Routine Checkup";

        assertThat(patient.getVisits()).isEmpty();

        //When
        patientDao.addVisitToPatient(patient.getId(), doctor.getId(), visitDate, description);

        //Then
        PatientEntity patientAfterAddingVisit = patientDao.findOne(patient.getId());
        assertThat(patientAfterAddingVisit.getVisits()).hasSize(1);

        VisitEntity addedVisit = visitDao.findOne(patientAfterAddingVisit.getVisits().get(0).getId());
        assertThat(addedVisit.getDescription()).isEqualTo(description);
        assertThat(addedVisit.getTime()).isEqualTo(visitDate);
        assertThat(addedVisit.getDoctor().getId()).isEqualTo(doctor.getId());
        assertThat(addedVisit.getPatient().getId()).isEqualTo(patient.getId());
    }

    @Test
    public void testFindBySurname() {
        // Given
        String surname = "Brown";

        // When
        List<PatientEntity> patients = patientDao.findBySurname(surname);

        // Then
        assertThat(patients).isNotEmpty();
        assertThat(patients).allMatch(patient -> surname.equals(patient.getLastName()));
    }


    @Test
    @Transactional
    public void testFindPatientsWithMoreThanXVisits(){

        //Given
        long visits = 1L;

        //When
        List<PatientEntity> patients = patientDao.findPatientsWithMoreThanXVisits(visits);

        //Then
        assertThat(patients).isNotEmpty();
        assertThat(patients).allMatch(patient -> patient.getVisits().size() > visits);

    }

    @Test
    public void testFindPatientsByActiveStatus(){

        //Given
        boolean active = true;

        //When
        List<PatientEntity> patients = patientDao.findPatientsByActiveStatus(active);

        //Then
        assertThat(patients).isNotEmpty();
        assertThat(patients).allMatch(patient -> patient.isActive() == active);
    }

}
