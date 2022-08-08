package com.afb.DocApp.domain.dto.Patient;

import com.afb.DocApp.domain.dto.MedicalHistory.GetMedicalHistoryResource;
import com.afb.DocApp.domain.model.MedicalHistory;
import com.afb.DocApp.domain.model.Patient;
import com.afb.DocApp.domain.model.User;
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
public class GetPatientResource {
    private Long id;
    private String name;
    private String lastname;
    private String gender;
    private Long numberPhone;
    private String user;

    public GetPatientResource(Patient patient){
        this.id = patient.getId();
        this.name = patient.getName();
        this.lastname = patient.getLastname();
        this.gender = patient.getGender();
        this.numberPhone = patient.getNumberPhone();
        this.user = patient.getUser().getName() + ' ' + patient.getUser().getLastname();

    }

    public static List<GetPatientResource> convert(List<Patient> patients){
        return patients.stream().map(GetPatientResource::new).collect(Collectors.toList());
    }
}
