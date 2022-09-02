package com.afb.DocApp.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class Login {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    public Authentication convert(){
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
