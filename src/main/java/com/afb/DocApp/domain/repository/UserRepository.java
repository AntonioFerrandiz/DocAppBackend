package com.afb.DocApp.domain.repository;

import com.afb.DocApp.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select count(mh.id) from User u join Patient p on u.id = p.user.id join MedicalHistory mh on p.id = mh.patient.id where u.id = :userId")
    Integer getTotalOfMedicalHistoriesByUser(Long userId);

    User findByEmail(String username);
}
