package com.afb.DocApp.domain.repository;

import com.afb.DocApp.domain.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findAppointmentsByPatient_User_Id(Long userId);
}
