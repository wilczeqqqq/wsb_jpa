package com.jpacourse.dto;

import com.jpacourse.persistence.enums.TreatmentType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class VisitTO implements Serializable {

    private LocalDateTime time;
    private DoctorTO doctor;
    private List<TreatmentType> medicalTreatment;

}
