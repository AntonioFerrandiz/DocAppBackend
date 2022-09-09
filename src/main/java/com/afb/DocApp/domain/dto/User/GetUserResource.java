package com.afb.DocApp.domain.dto.User;

import com.afb.DocApp.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserResource {

    private String fullname;
    private Boolean active;

    private String email;

    private Long numberPhone;

    private String medicalSpeciality;

    private Long CMP;

    private String medicalOffice;
    public GetUserResource(User user) {
        this.fullname = user.getName() + ' ' + user.getLastname();
        this.active = user.getActive();
        this.email = user.getEmail();
        this.numberPhone = user.getNumberPhone();
        this.medicalSpeciality = user.getMedicalSpeciality();
        this.CMP = user.getCMP();
        this.medicalOffice = user.getMedicalOffice();
    }

    public static List<GetUserResource> convert(List<User> users) {
        return users.stream().map(GetUserResource::new).collect(Collectors.toList());
    }
}
