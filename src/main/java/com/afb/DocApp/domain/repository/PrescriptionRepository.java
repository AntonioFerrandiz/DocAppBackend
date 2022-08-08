package com.afb.DocApp.domain.repository;

import com.afb.DocApp.domain.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}
