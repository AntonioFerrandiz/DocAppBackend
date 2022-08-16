package com.afb.DocApp.domain.dto.Patient;

import com.afb.DocApp.domain.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPatientGenderResource {
    private Integer patientsMale;
    private Integer patientsFemale;

}

