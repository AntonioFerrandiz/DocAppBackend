package com.afb.DocApp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.Timer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appointmentDate;
    private String appointmentStartTime;
    private String appointmentEndTime;
    @Enumerated(EnumType.STRING)
    private StatusAppointment statusAppointment;
    @ManyToOne(targetEntity = Patient.class)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Appointment(String appointmentDate, String appointmentStartTime, String appointmentEndTime, StatusAppointment statusAppointment, Optional<Patient> patient){
        LocalDateTime now = LocalDateTime.now();
        Locale locale = new Locale("es","ES");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy",locale);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        //this.appointmentDate = now.format(dateFormatter);
        //this.appointmentStartTime = now.format(timeFormatter);
        //this.appointmentEndTime = now.format(timeFormatter);
        this.appointmentDate = appointmentDate;
        this.appointmentStartTime = appointmentStartTime;
        this.appointmentEndTime = appointmentEndTime;
        patient.ifPresent(p -> this.patient = p);
        this.statusAppointment = StatusAppointment.ACTIVO;
    }
}
