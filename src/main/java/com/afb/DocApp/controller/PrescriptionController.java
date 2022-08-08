package com.afb.DocApp.controller;

import com.afb.DocApp.domain.dto.Prescription.CreatePrescriptionResource;
import com.afb.DocApp.domain.dto.Prescription.GetPrescriptionResource;
import com.afb.DocApp.domain.dto.User.CreateUserResource;
import com.afb.DocApp.domain.dto.User.GetUserResource;
import com.afb.DocApp.domain.model.Prescription;
import com.afb.DocApp.domain.model.User;
import com.afb.DocApp.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/prescription")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping
    public ResponseEntity<CreatePrescriptionResource> registerPrescription(@Valid @RequestBody CreatePrescriptionResource prescriptionResource, UriComponentsBuilder uriComponentsBuilder){
        Prescription prescription = prescriptionService.save(prescriptionResource);
        URI uri = uriComponentsBuilder.path("/prescription/{id}").buildAndExpand(prescription.getId()).toUri();
        return ResponseEntity.created(uri).body(new CreatePrescriptionResource(prescription));
    }

    @GetMapping
    public ResponseEntity<List<GetPrescriptionResource>> getAllPrescriptions(){
        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPrescriptionResource> getPrescription(@PathVariable Long id){
        GetPrescriptionResource prescription = prescriptionService.getPrescription(id);
        return ResponseEntity.ok(prescription);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePrescription (@PathVariable Long id){
        prescriptionService.deletePrescription(id);
        return ResponseEntity.ok().build();
    }
}
