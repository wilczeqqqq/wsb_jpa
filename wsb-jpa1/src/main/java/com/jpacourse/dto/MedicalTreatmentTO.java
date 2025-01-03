package com.jpacourse.dto;

import com.jpacourse.persistence.enums.TreatmentType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class MedicalTreatmentTO implements Serializable {

    private TreatmentType type;

}
