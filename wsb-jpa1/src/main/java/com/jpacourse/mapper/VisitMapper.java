package com.jpacourse.mapper;

import com.jpacourse.dto.MedicalTreatmentTO;
import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.stream.Collectors;

public final class VisitMapper
{

    private VisitMapper() {
    }

    public static VisitTO mapToTO(final VisitEntity visitEntity) {
        return VisitTO.builder()
                .time(visitEntity.getTime())
                .doctor(DoctorMapper.mapToTO(visitEntity.getDoctor()))
                .medicalTreatment(visitEntity.getMedicalTreatment().stream()
                        .map(medicalTreatment -> MedicalTreatmentMapper.mapToTO(medicalTreatment).getType())
                        .collect(Collectors.toList()))
                .build();
    }

    public static VisitEntity mapToEntity(final VisitTO visitTO) {
        VisitEntity visit = new VisitEntity();
        visit.setTime(visitTO.getTime());
        visit.setDoctor(DoctorMapper.mapToEntity(visitTO.getDoctor()));
        visit.setMedicalTreatment(visitTO.getMedicalTreatment().stream()
                .map(medicalTreatment -> {
                    MedicalTreatmentTO medicalTreatmentTO = MedicalTreatmentTO.builder()
                            .type(medicalTreatment)
                            .build();
                    return MedicalTreatmentMapper.mapToEntity(medicalTreatmentTO);
                })
                .collect(Collectors.toList()));

        return visit;
    }

}
