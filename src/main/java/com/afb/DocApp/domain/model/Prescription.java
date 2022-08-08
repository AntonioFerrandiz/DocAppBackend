package com.afb.DocApp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prescriptions")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String prescribedMedication;

    public Prescription(LocalDateTime dateCreated, String description, String prescribedMedication) {
        this.dateCreated = LocalDateTime.now();
        this.description = description;
        this.prescribedMedication = prescribedMedication;
    }
}
