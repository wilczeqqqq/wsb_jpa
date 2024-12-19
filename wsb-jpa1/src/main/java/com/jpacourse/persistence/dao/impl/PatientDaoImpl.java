package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao
{
        @Override
        public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description) {
            PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
            DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);

            if (patient == null) {
                throw new EntityNotFoundException(patientId);
            } else if (doctor == null) {
                throw new EntityNotFoundException(doctorId);
            }

            VisitEntity visit = new VisitEntity();
            visit.setDoctor(doctor);
            visit.setPatient(patient);
            visit.setDescription(description);
            visit.setTime(visitDate);

            patient.getVisits().add(visit);
            entityManager.merge(patient);
        }
    }

