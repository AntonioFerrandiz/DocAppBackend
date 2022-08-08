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
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMedicalHistoryResource {
    private Long id;

    private LocalDateTime dateCreated;

    private String description;
    private Integer age;

    @NotNull
    @NotBlank
    private String ageMeasurement;

    private Integer weight;

    @NotNull
    @NotBlank
    private String weightMeasurement;

    private Integer height;

    @NotNull
    @NotBlank
    private String heightMeasurement;

    private Long patientId;

    public CreateMedicalHistoryResource(MedicalHistory medicalHistory){
        this.id = medicalHistory.getId();
        this.dateCreated = medicalHistory.getDateCreated();
        this.description = medicalHistory.getDescription();
        this.age = medicalHistory.getAge();
        this.ageMeasurement = medicalHistory.getAgeMeasurement();
        this.weight = medicalHistory.getWeight();
        this.weightMeasurement = medicalHistory.getWeightMeasurement();
        this.height = medicalHistory.getHeight();
        this.heightMeasurement = medicalHistory.getHeightMeasurement();
    }

    public MedicalHistory convert(Optional<Patient> patient){
        return new MedicalHistory(dateCreated, description, age, ageMeasurement, weight, weightMeasurement, height, heightMeasurement, patient);
    }
}
