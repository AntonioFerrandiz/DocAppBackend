package com.afb.DocApp.domain.dto.Appointment;

import com.afb.DocApp.domain.model.Appointment;
import com.afb.DocApp.domain.model.Patient;
import com.afb.DocApp.domain.model.StatusAppointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAppointmentResource {
    private Long id;

    private String appointmentDate;
    private String appointmentStartTime;
    private String appointmentEndTime;
    private StatusAppointment statusAppointment;

    private Long patientId;

    public CreateAppointmentResource(Appointment appointment) {
        this.id = appointment.getId();
        this.appointmentDate = appointment.getAppointmentDate();
        this.appointmentStartTime = appointment.getAppointmentStartTime();
        this.appointmentEndTime = appointment.getAppointmentEndTime();
        this.statusAppointment = appointment.getStatusAppointment();
    }
    public Appointment convert(Optional<Patient> patient){
        return new Appointment(appointmentDate, appointmentStartTime,appointmentEndTime,statusAppointment,patient);
    }
}
