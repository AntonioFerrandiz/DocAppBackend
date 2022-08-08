package com.afb.DocApp.domain.repository;

import com.afb.DocApp.domain.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
    List<MedicalHistory> findMedicalHistoriesByPatientId(Long patientId);
}
