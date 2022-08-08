package com.afb.DocApp.domain.dto.Prescription;

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
public class GetPrescriptionResource {
    private Long id;

    private LocalDateTime dateCreated;

    private String description;

    private String prescribedMedication;

    public GetPrescriptionResource(Prescription prescription){
        this.id = prescription.getId();
        this.dateCreated = prescription.getDateCreated();
        this.description = prescription.getDescription();
        this.prescribedMedication = prescription.getPrescribedMedication();
    }

    public static List<GetPrescriptionResource> convert(List<Prescription> prescriptions){
        return prescriptions.stream().map(GetPrescriptionResource::new).collect(Collectors.toList());
    }
}
