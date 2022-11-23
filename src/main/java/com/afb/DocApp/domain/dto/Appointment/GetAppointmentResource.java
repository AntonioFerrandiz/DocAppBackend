package com.afb.DocApp.domain.dto.Appointment;

import com.afb.DocApp.domain.model.Appointment;
import com.afb.DocApp.domain.model.StatusAppointment;
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
public class GetAppointmentResource {
    private Long id;

    private String appointmentDate;
    private String appointmentStartTime;
    private String appointmentEndTime;
    private StatusAppointment statusAppointment;

    private Long patientId;
    private String patientFullName;

    public GetAppointmentResource(Appointment appointment) {
        this.id = appointment.getId();
        this.appointmentDate = appointment.getAppointmentDate();
        this.appointmentStartTime = appointment.getAppointmentStartTime();
        this.appointmentEndTime = appointment.getAppointmentEndTime();
        this.statusAppointment = appointment.getStatusAppointment();
        this.patientId = appointment.getPatient().getId();
        this.patientFullName = appointment.getPatient().getFullname();
    }
    public static List<GetAppointmentResource> convert(List<Appointment> appointments){
        return appointments.stream().map(GetAppointmentResource::new).collect(Collectors.toList());
    }
}

