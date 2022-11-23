package com.afb.DocApp.controller;

import com.afb.DocApp.domain.dto.Appointment.CreateAppointmentResource;
import com.afb.DocApp.domain.dto.Appointment.GetAppointmentResource;
import com.afb.DocApp.domain.dto.MedicalHistory.CreateMedicalHistoryResource;
import com.afb.DocApp.domain.dto.MedicalHistory.GetMedicalHistoryResource;
import com.afb.DocApp.domain.model.Appointment;
import com.afb.DocApp.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping()
    public ResponseEntity<CreateAppointmentResource> registerAppointment(@Valid @RequestBody CreateAppointmentResource resource, UriComponentsBuilder uriComponentsBuilder){
        Appointment appointment = appointmentService.save(resource);
        URI uri = uriComponentsBuilder.path("/appointment/{id}").buildAndExpand(appointment.getId()).toUri();
        return ResponseEntity.created(uri).body(new CreateAppointmentResource(appointment));
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<GetAppointmentResource>> getAppointmentsByUser(@PathVariable Long userId){
        return ResponseEntity.ok(appointmentService.getAppointmentResource(userId));
    }
}
