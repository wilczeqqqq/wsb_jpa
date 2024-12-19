package com.jpacourse.mapper;

import com.jpacourse.dto.DoctorTO;
import com.jpacourse.persistence.entity.DoctorEntity;

public final class DoctorMapper
{

    private DoctorMapper() {
    }

    public static DoctorTO mapToTO(final DoctorEntity doctorEntity) {
        return DoctorTO.builder()
                .firstName(doctorEntity.getFirstName())
                .lastName(doctorEntity.getLastName())
                .build();
    }


    public static DoctorEntity mapToEntity(final DoctorTO doctorTO) {
        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName(doctorTO.getFirstName());
        doctor.setLastName(doctorTO.getLastName());

        return doctor;
    }

}
