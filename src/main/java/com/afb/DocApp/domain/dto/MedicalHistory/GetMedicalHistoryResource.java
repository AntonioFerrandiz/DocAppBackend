package com.afb.DocApp.domain.dto.MedicalHistory;

import com.afb.DocApp.domain.model.MedicalHistory;
import com.afb.DocApp.domain.model.Patient;
import com.afb.DocApp.domain.model.Prescription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetMedicalHistoryResource {
    private Long id;

    private LocalDateTime dateCreated;

    private String description;
    private Integer age;

    private String ageMeasurement;

    private Integer weight;

    private String weightMeasurement;

    private Integer height;

    private String heightMeasurement;

    private String patientName;


    public GetMedicalHistoryResource(MedicalHistory medicalHistory){
        this.id = medicalHistory.getId();
        this.dateCreated = medicalHistory.getDateCreated();
        this.description = medicalHistory.getDescription();
        this.age = medicalHistory.getAge();
        this.ageMeasurement = medicalHistory.getAgeMeasurement();
        this.weight = medicalHistory.getWeight();
        this.weightMeasurement = medicalHistory.getWeightMeasurement();
        this.height = medicalHistory.getHeight();
        this.heightMeasurement = medicalHistory.getHeightMeasurement();
        this.patientName = medicalHistory.getPatient().getFullname();
    }


    public static List<GetMedicalHistoryResource> convert(List<MedicalHistory> medicalHistories){
        return medicalHistories.stream().map(GetMedicalHistoryResource::new).collect(Collectors.toList());
    }
}
