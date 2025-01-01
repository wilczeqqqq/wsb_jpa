package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.jpacourse.persistence.enums.Specialization.SURGEON;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testAddVisitToPatient(){

        // Given
        AddressEntity address = new AddressEntity();
        address.setCity("Przytuły");
        entityManager.persist(address);

        PatientEntity patient = new PatientEntity();
        patient.setFirstName("Jakub");
        patient.setLastName("Tuna");
        patient.setTelephoneNumber("123456789");
        patient.setEmail("tunaboi@example.com");
        patient.setPatientNumber("123");
        patient.setDateOfBirth(LocalDate.of(2001, 1, 3));
        patient.setActive(true);
        patient.setVisits(new ArrayList<>());
        patient.setAddress(address);

        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName("Gregory");
        doctor.setLastName("House");
        doctor.setTelephoneNumber("987654321");
        doctor.setDoctorNumber("123");
        doctor.setSpecialization(SURGEON);
        doctor.setAddress(address);

        entityManager.persist(doctor);
        entityManager.persist(patient);

        LocalDateTime visitDate = LocalDateTime.now();
        String description = "Routine Checkup";

        //When
        patientDao.addVisitToPatient(patient.getId(), doctor.getId(), visitDate, description);

        //Then
        PatientEntity patientAfterAddingVisit = patientDao.findOne(patient.getId());
        assertThat(patientAfterAddingVisit.getVisits()).hasSize(1);

        VisitEntity addedVisit = visitDao.getOne(patientAfterAddingVisit.getVisits().get(0).getId());
        assertThat(addedVisit.getDescription()).isEqualTo(description);
        assertThat(addedVisit.getTime()).isEqualTo(visitDate);
        assertThat(addedVisit.getDoctor().getId()).isEqualTo(doctor.getId());
        assertThat(addedVisit.getPatient().getId()).isEqualTo(patient.getId());
    }

}
