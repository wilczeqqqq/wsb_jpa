package com.jpacourse.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class DoctorTO implements Serializable {

    private String firstName;
    private String lastName;

}
