package com.jpacourse.mapper;

import com.jpacourse.dto.MedicalTreatmentTO;
import com.jpacourse.persistence.entity.MedicalTreatmentEntity;

public final class MedicalTreatmentMapper
{

    private MedicalTreatmentMapper() {
    }

    public static MedicalTreatmentTO mapToTO(final MedicalTreatmentEntity medicalTreatmentEntity) {
        return MedicalTreatmentTO.builder()
                .type(medicalTreatmentEntity.getType())
                .build();
    }


    public static MedicalTreatmentEntity mapToEntity(final MedicalTreatmentTO medicalTreatmentTO) {
        MedicalTreatmentEntity medicalTreatment = new MedicalTreatmentEntity();
        medicalTreatment.setType(medicalTreatmentTO.getType());

        return medicalTreatment;
    }

}
