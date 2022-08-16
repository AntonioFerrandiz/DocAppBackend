package com.afb.DocApp.controller;

import com.afb.DocApp.domain.dto.Patient.CreatePatientResource;
import com.afb.DocApp.domain.dto.Patient.GetPatientGenderResource;
import com.afb.DocApp.domain.dto.Patient.GetPatientResource;
import com.afb.DocApp.domain.dto.Patient.UpdatePatientResource;
import com.afb.DocApp.domain.dto.User.CreateUserResource;
import com.afb.DocApp.domain.model.Patient;
import com.afb.DocApp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping()
    public ResponseEntity<CreatePatientResource> registerPatient(@Valid @RequestBody CreatePatientResource patientResource, UriComponentsBuilder uriComponentsBuilder){
        Patient patient = patientService.save(patientResource);
        URI uri = uriComponentsBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new CreatePatientResource(patient));
    }

    @GetMapping
    public ResponseEntity<List<GetPatientResource>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPatientResource> getPatientById(@PathVariable Long id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @GetMapping("/getUserPatients/{userId}")
    public ResponseEntity<List<GetPatientResource>> getPatientsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(patientService.getPatientsByUserId(userId));
    }

    @GetMapping("/searchPatientByFullname/{patientFullname}")
    public ResponseEntity<List<GetPatientResource>> getPatientsByFullName(@PathVariable String patientFullname){
       return ResponseEntity.ok(patientService.getPatientsByFullName(patientFullname));
    }

    @GetMapping("/getUserPatients/{userId}/findPatientsByGender")
    public ResponseEntity<GetPatientGenderResource> findPatientsByGender(String gender, @PathVariable Long userId){
        return ResponseEntity.ok(patientService.findPatientsByGender(gender,userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePatient(@PathVariable Long id, @RequestBody @Valid UpdatePatientResource updatePatientResource){
        Patient patient = patientService.updatePatient(id, updatePatientResource);
        return ResponseEntity.ok(new GetPatientResource(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return ResponseEntity.ok().build();
    }

}
