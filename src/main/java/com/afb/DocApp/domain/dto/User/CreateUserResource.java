package com.afb.DocApp.domain.dto.User;

import com.afb.DocApp.domain.model.User;
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
public class CreateUserResource {
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String lastname;

    @NotNull
    private Long dni;

    private String email;

    private Long numberPhone;

    private String medicalSpeciality;

    private Long CMP;

    private String medicalOffice;
    private String password;
    private LocalDateTime dateCreated;

    private Boolean active;

    public CreateUserResource(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.dni = user.getDni();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.dateCreated = user.getDateCreated();
        this.active = user.getActive();
        this.numberPhone = user.getNumberPhone();
        this.medicalSpeciality = user.getMedicalSpeciality();
        this.CMP = user.getCMP();
        this.medicalOffice = user.getMedicalOffice();
    }

    public User convert() {
        return new User(name, lastname, dni, email, password, dateCreated, active,
                        numberPhone, medicalSpeciality, CMP, medicalOffice);
    }
}
