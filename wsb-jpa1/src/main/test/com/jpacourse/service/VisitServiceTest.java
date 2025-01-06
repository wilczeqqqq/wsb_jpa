package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.PatientDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VisitServiceTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private VisitService visitService;

    @Transactional
    @Test
    public void testFindVisitsByPatientId() {
        //Given
        final Long patientId = 4L;
        final PatientTO patient = PatientMapper.mapToTO(patientDao.findOne(patientId));

        //When
        final List<VisitTO> visits = visitService.findVisitsByPatientId(patientId);

        //Then
        assertThat(visits).hasSize(patient.getVisits().size());
        assertThat(visits).containsExactlyInAnyOrderElementsOf(patient.getVisits());
    }
}
