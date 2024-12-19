package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.PatientEntity;

import java.util.stream.Collectors;

public final class PatientMapper
{

    private PatientMapper() {
    }

    public static PatientTO mapToTO(final PatientEntity patientEntity) {
        return PatientTO.builder()
                .id(patientEntity.getId())
                .firstName(patientEntity.getFirstName())
                .lastName(patientEntity.getLastName())
                .email(patientEntity.getEmail())
                .patientNumber(patientEntity.getPatientNumber())
                .active(patientEntity.isActive())
                .dateOfBirth(patientEntity.getDateOfBirth())
                .telephoneNumber(patientEntity.getTelephoneNumber())
                .visits(patientEntity.getVisits().stream()
                        .map(VisitMapper::mapToTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public static PatientEntity mapToEntity(final PatientTO patientTO) {
        PatientEntity patient = new PatientEntity();
        patient.setId(patientTO.getId());
        patient.setFirstName(patientTO.getFirstName());
        patient.setLastName(patientTO.getLastName());
        patient.setEmail(patientTO.getEmail());
        patient.setPatientNumber(patientTO.getPatientNumber());
        patient.setActive(patientTO.isActive());
        patient.setDateOfBirth(patientTO.getDateOfBirth());
        patient.setTelephoneNumber(patientTO.getTelephoneNumber());
        patient.setVisits(patientTO.getVisits().stream()
                .map(VisitMapper::mapToEntity)
                .collect(Collectors.toList()));

        return patient;
    }

}
