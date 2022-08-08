package com.afb.DocApp.domain.dto.Prescription;

import com.afb.DocApp.domain.model.Prescription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePrescriptionResource {
    private Long id;

    private LocalDateTime dateCreated;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String prescribedMedication;

    public CreatePrescriptionResource(Prescription prescription){
        this.id = prescription.getId();
        this.dateCreated = prescription.getDateCreated();
        this.description = prescription.getDescription();
        this.prescribedMedication = prescription.getPrescribedMedication();
    }

    public Prescription convert(){
        return new Prescription(dateCreated,description,prescribedMedication);
    }
}
