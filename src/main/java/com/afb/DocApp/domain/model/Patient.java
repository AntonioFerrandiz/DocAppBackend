package com.afb.DocApp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String lastname;

    private String fullname;
    @NotNull
    @NotBlank
    private String gender;

    @NotNull
    private Long numberPhone;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    public Patient(String name, String lastname, String gender, Long numberPhone, Optional<User> user) {
        this.name = name;
        this.lastname = lastname;
        this.fullname = name + ' ' + lastname;
        this.gender = gender;
        this.numberPhone = numberPhone;
        user.ifPresent(u -> this.user = u);
    }

}
