package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.dto.PatientVisitsTO;

import java.util.List;


public interface PatientService
{
    PatientTO findById(final Long id);

    void deleteById(final Long id);

    PatientVisitsTO findVisitsByPatientId(final Long patientId);
}
