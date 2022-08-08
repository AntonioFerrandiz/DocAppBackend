package com.afb.DocApp.controller;

import com.afb.DocApp.domain.dto.MedicalHistory.CreateMedicalHistoryResource;
import com.afb.DocApp.domain.dto.MedicalHistory.GetMedicalHistoryResource;
import com.afb.DocApp.domain.dto.MedicalHistory.UpdateMedicalHistoryResource;
import com.afb.DocApp.domain.dto.Patient.CreatePatientResource;
import com.afb.DocApp.domain.model.MedicalHistory;
import com.afb.DocApp.service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/medicalHistory")
public class MedicalHistoryController {
    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @PostMapping()
    public ResponseEntity<CreateMedicalHistoryResource> registerMedicalHistory(@Valid @RequestBody CreateMedicalHistoryResource resource, UriComponentsBuilder uriComponentsBuilder){
        MedicalHistory medicalHistory = medicalHistoryService.save(resource);
        URI uri = uriComponentsBuilder.path("/medicalHistory/{id}").buildAndExpand(medicalHistory.getId()).toUri();
        return ResponseEntity.created(uri).body(new CreateMedicalHistoryResource(medicalHistory));
    }

    @GetMapping
    public ResponseEntity<List<GetMedicalHistoryResource>> getAllMedicalHistories(){
        return ResponseEntity.ok(medicalHistoryService.getAllMedicalHistories());
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<GetMedicalHistoryResource>> getAllMedicalHistoriesByPatient(@PathVariable Long patientId){
        return ResponseEntity.ok(medicalHistoryService.getAllMedicalHistoriesByPatient(patientId));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMedicalHistory(@PathVariable Long id, @Valid @RequestBody UpdateMedicalHistoryResource updateMedicalHistoryResource){
        MedicalHistory medicalHistory = medicalHistoryService.updateMedicalHistory(id, updateMedicalHistoryResource);
        return ResponseEntity.ok(new GetMedicalHistoryResource(medicalHistory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMedicalHistory(@PathVariable Long id){
        medicalHistoryService.deleteMedicalHistory(id);
        return ResponseEntity.ok().build();
    }
}
