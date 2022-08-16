package com.afb.DocApp.service;

import com.afb.DocApp.domain.dto.Patient.CreatePatientResource;
import com.afb.DocApp.domain.dto.Patient.GetPatientGenderResource;
import com.afb.DocApp.domain.dto.Patient.GetPatientResource;
import com.afb.DocApp.domain.dto.Patient.UpdatePatientResource;
import com.afb.DocApp.domain.model.Patient;
import com.afb.DocApp.domain.model.User;
import com.afb.DocApp.domain.repository.PatientRepository;
import com.afb.DocApp.domain.repository.UserRepository;
import com.afb.DocApp.shared.exception.ResourceNotFoundException;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private static final String ENTITY = "Paciente";

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserRepository userRepository;

    public Patient save(CreatePatientResource resource){
        Optional<User> user = userRepository.findById(resource.getUserId());
        Patient patient = resource.convert(user);
        return patientRepository.save(patient);
    }

    public List<GetPatientResource> getAllPatients() {
        List<Patient> patients;
        patients = patientRepository.findAll();
        return GetPatientResource.convert(patients);
    }

    public GetPatientResource getPatientById(Long id){
        Optional<Patient> patient = patientRepository.findById(id);
        if(!patient.isPresent()){
            throw new ResourceNotFoundException(ENTITY, id);
        }
        return new GetPatientResource(patient.get());
    }
    public List<GetPatientResource> getPatientsByUserId(Long userId){
        List<Patient> patients;
        patients = patientRepository.findByUser_Id(userId);
        return GetPatientResource.convert(patients);
    }

    public List<GetPatientResource> getPatientsByFullName(String patientFullname){
        List<Patient> patients;
        patients = patientRepository.findPatientsByFullnameContainsIgnoreCase(patientFullname);
        return GetPatientResource.convert(patients);
    }

    public GetPatientGenderResource findPatientsByGender(String gender, Long userId){
        Integer patientsM, patientsF;

        patientsM = patientRepository.findPatientByGenderAndUser_Id("Masculino", userId).size();
        patientsF = patientRepository.findPatientByGenderAndUser_Id("Femenino", userId).size();


        GetPatientGenderResource getPatientGenderResource = new GetPatientGenderResource();
        getPatientGenderResource.setPatientsMale(patientsM);
        getPatientGenderResource.setPatientsFemale(patientsF);
        return getPatientGenderResource;
    }

    @Transactional
    public Patient updatePatient(Long id, @Valid UpdatePatientResource updatePatientResource){
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if(!optionalPatient.isPresent()){
            throw new ResourceNotFoundException(ENTITY, id);
        }
        Patient patient = optionalPatient.get();
        patient.setName(updatePatientResource.getName());
        patient.setLastname(updatePatientResource.getLastname());
        patient.setNumberPhone(updatePatientResource.getNumberPhone());
        patient.setFullname(updatePatientResource.getName() + ' ' + updatePatientResource.getLastname());
        return patient;
    }

    public void deletePatient(Long id){
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if(!optionalPatient.isPresent()){
            throw new ResourceNotFoundException(ENTITY, id);
        }
        patientRepository.deleteById(id);
    }
}
