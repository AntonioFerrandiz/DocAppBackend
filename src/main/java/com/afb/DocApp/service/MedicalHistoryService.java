package com.afb.DocApp.service;

import com.afb.DocApp.domain.dto.MedicalHistory.CreateMedicalHistoryResource;
import com.afb.DocApp.domain.dto.MedicalHistory.GetMedicalHistoryResource;
import com.afb.DocApp.domain.dto.MedicalHistory.UpdateMedicalHistoryResource;
import com.afb.DocApp.domain.model.MedicalHistory;
import com.afb.DocApp.domain.model.Patient;
import com.afb.DocApp.domain.model.Prescription;
import com.afb.DocApp.domain.repository.MedicalHistoryRepository;
import com.afb.DocApp.domain.repository.PatientRepository;
import com.afb.DocApp.domain.repository.PrescriptionRepository;
import com.afb.DocApp.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalHistoryService {
    private static final String ENTITY = "Historial Médico";

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public MedicalHistory save(CreateMedicalHistoryResource resource){
        Optional<Patient> patient = patientRepository.findById(resource.getPatientId());
        if(!patient.isPresent()){
            throw new ResourceNotFoundException("Paciente", resource.getPatientId());
        }
        MedicalHistory medicalHistory = resource.convert(patient);
        return medicalHistoryRepository.save(medicalHistory);

    }

    public List<GetMedicalHistoryResource> getAllMedicalHistories(){
        List<MedicalHistory> medicalHistories;
        medicalHistories = medicalHistoryRepository.findAll();
        return GetMedicalHistoryResource.convert(medicalHistories);
    }

    public List<GetMedicalHistoryResource> getAllMedicalHistoriesByPatient(Long patientId){
        List<MedicalHistory> medicalHistories;
        medicalHistories = medicalHistoryRepository.findMedicalHistoriesByPatientId(patientId);
        return GetMedicalHistoryResource.convert(medicalHistories);
    }

    public MedicalHistory updateMedicalHistory(Long id, @Valid UpdateMedicalHistoryResource updateMedicalHistoryResource){
        Optional<MedicalHistory> optionalMedicalHistory = medicalHistoryRepository.findById(id);
        if(!optionalMedicalHistory.isPresent()){
            throw new ResourceNotFoundException(ENTITY, id);
        }

        MedicalHistory medicalHistory = optionalMedicalHistory.get();

        medicalHistory.setDescription(updateMedicalHistoryResource.getDescription());
        medicalHistory.setAge(updateMedicalHistoryResource.getAge());
        medicalHistory.setAgeMeasurement(updateMedicalHistoryResource.getAgeMeasurement());
        medicalHistory.setWeight(updateMedicalHistoryResource.getWeight());
        medicalHistory.setWeightMeasurement(updateMedicalHistoryResource.getWeightMeasurement());
        medicalHistory.setHeight(updateMedicalHistoryResource.getHeight());
        medicalHistory.setHeightMeasurement(updateMedicalHistoryResource.getHeightMeasurement());

        return medicalHistory;
    }

    public void deleteMedicalHistory(Long id){
        Optional<MedicalHistory> optionalMedicalHistory = medicalHistoryRepository.findById(id);
        if(!optionalMedicalHistory.isPresent()){
            throw new ResourceNotFoundException(ENTITY, id);
        }

        medicalHistoryRepository.deleteById(id);
    }
}
