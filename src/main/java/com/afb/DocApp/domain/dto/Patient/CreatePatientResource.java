package com.afb.DocApp.domain.dto.Patient;

import com.afb.DocApp.domain.model.Patient;
import com.afb.DocApp.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientResource {
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String lastname;

    @NotNull
    @NotBlank
    private String gender;

    @NotNull
    private Long numberPhone;

    private Long userId;

    public CreatePatientResource(Patient patient){
        this.id = patient.getId();
        this.name = patient.getName();
        this.lastname = patient.getLastname();
        this.gender = patient.getGender();
        this.numberPhone = patient.getNumberPhone();
    }

    public Patient convert(Optional<User> user){
        return new Patient(name,lastname,gender,numberPhone,user);
    }
}
