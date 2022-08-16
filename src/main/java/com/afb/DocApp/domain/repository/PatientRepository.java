package com.afb.DocApp.domain.repository;

import com.afb.DocApp.domain.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByUser_Id(Long userId);

    List<Patient> findPatientsByFullnameContainsIgnoreCase(String patientFullname);

    List<Patient> findPatientByGenderAndUser_Id(String gender, Long userId);
}
