package com.afb.DocApp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "medicalHistories")
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    private Integer age;

    @NotNull
    @NotBlank
    private String description;

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

    @ManyToOne(targetEntity = Patient.class)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public MedicalHistory(LocalDateTime dateCreated, String description, Integer age, String ageMeasurement, Integer weight, String weightMeasurement, Integer height, String heightMeasurement, Optional<Patient> patient) {
        this.dateCreated = LocalDateTime.now();
        this.description = description;
        this.age = age;
        this.ageMeasurement = ageMeasurement;
        this.weight = weight;
        this.weightMeasurement = weightMeasurement;
        this.height = height;
        this.heightMeasurement = heightMeasurement;
        patient.ifPresent(p -> this.patient = p);
    }
}
