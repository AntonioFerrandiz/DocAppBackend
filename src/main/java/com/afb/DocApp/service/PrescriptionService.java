package com.afb.DocApp.service;

import com.afb.DocApp.domain.dto.Prescription.CreatePrescriptionResource;
import com.afb.DocApp.domain.dto.Prescription.GetPrescriptionResource;
import com.afb.DocApp.domain.model.Prescription;
import com.afb.DocApp.domain.repository.PrescriptionRepository;
import com.afb.DocApp.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {

    private static final String ENTITY = "Prescripción";
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public Prescription save(CreatePrescriptionResource resource){
        Prescription prescription = resource.convert();


        return prescriptionRepository.save(prescription);
    }

    public List<GetPrescriptionResource> getAllPrescriptions(){
        List<Prescription> prescriptions;
        prescriptions = prescriptionRepository.findAll();
        return GetPrescriptionResource.convert(prescriptions);
    }

    public GetPrescriptionResource getPrescription(Long id){
        Optional<Prescription> prescription = prescriptionRepository.findById(id);
        if (!prescription.isPresent()){
            throw new ResourceNotFoundException(ENTITY ,id);
        }
        return new GetPrescriptionResource(prescription.get());
    }

    public void deletePrescription(Long id){
        Optional<Prescription> optionalPrescription = prescriptionRepository.findById(id);
        if (!optionalPrescription.isPresent()){
            throw new ResourceNotFoundException(ENTITY ,id);
        }
        prescriptionRepository.deleteById(id);
    }
}
