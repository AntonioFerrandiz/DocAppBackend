package com.afb.DocApp.domain.model;

import lombok.*;

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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String password;

    private LocalDateTime dateCreated;

    private Boolean active;


    public User(String name, String lastname, Long dni, String email, String password, LocalDateTime dateCreated, Boolean active) {
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.dateCreated = LocalDateTime.now();
        this.active = true;
    }
}
