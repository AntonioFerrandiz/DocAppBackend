package com.afb.DocApp.service;

import com.afb.DocApp.domain.dto.Appointment.CreateAppointmentResource;
import com.afb.DocApp.domain.dto.Appointment.GetAppointmentResource;
import com.afb.DocApp.domain.model.Appointment;
import com.afb.DocApp.domain.model.Patient;
import com.afb.DocApp.domain.repository.AppointmentRepository;
import com.afb.DocApp.domain.repository.PatientRepository;
import com.afb.DocApp.shared.exception.ResourceDateException;
import com.afb.DocApp.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private static final String ENTITY = "Citas";

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    public Appointment save(CreateAppointmentResource resource){
        Optional<Patient> patient = patientRepository.findById(resource.getPatientId());
        if(!patient.isPresent()){
            throw new ResourceNotFoundException("Paciente", resource.getPatientId());
        }
        Appointment appointment = resource.convert(patient);
        return appointmentRepository.save(appointment);
    }

    public List<GetAppointmentResource> getAppointmentResource(Long userId){
        List<Appointment> appointment = appointmentRepository.findAppointmentsByPatient_User_Id(userId);
        return GetAppointmentResource.convert(appointment);
    }
}
