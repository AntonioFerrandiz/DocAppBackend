package com.afb.DocApp.domain.dto.MedicalHistory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMedicalHistoryResource {
    private String description;

    private Integer age;

    private String ageMeasurement;

    private Integer weight;

    private String weightMeasurement;

    private Integer height;

    private String heightMeasurement;
}
