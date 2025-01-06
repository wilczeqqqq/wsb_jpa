package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.rest.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao
{

    private final DoctorDao doctorDao;

    @Autowired
    public PatientDaoImpl(DoctorDao pDoctorDao) {doctorDao = pDoctorDao;}

    @Override
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description) {
        PatientEntity patient = findOne(patientId);
        DoctorEntity doctor = doctorDao.findOne(doctorId);

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
        update(patient);
    }

    @Override
    public List<PatientEntity> findBySurname(String surname) {
        return entityManager.createQuery("SELECT p FROM PatientEntity p WHERE p.lastName = :surname", PatientEntity.class)
                .setParameter("surname", surname)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsWithMoreThanXVisits(Long visits) {
        return entityManager.createQuery("SELECT p FROM PatientEntity p " +
                                            "JOIN p.visits v " +
                                            "GROUP BY p.id " +
                                            "HAVING COUNT(v) > :visits", PatientEntity.class)
                .setParameter("visits", visits)
                .getResultList();
    }

    // No other way for boolean variable...
    @Override
    public List<PatientEntity> findPatientsByActiveStatus(boolean status) {
        return entityManager.createQuery("SELECT p FROM PatientEntity p WHERE p.active = :status", PatientEntity.class)
                .setParameter("status", status)
                .getResultList();
    }
}
