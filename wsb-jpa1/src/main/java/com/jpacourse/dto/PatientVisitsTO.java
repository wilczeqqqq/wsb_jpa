package com.jpacourse.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PatientVisitsTO implements Serializable {

    private List<VisitTO> visits;

}
