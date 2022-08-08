package com.afb.DocApp.domain.dto.Patient;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UpdatePatientResource {
    @NotEmpty
    private String name;

    @NotEmpty
    private String lastname;

    private Long numberPhone;

}
