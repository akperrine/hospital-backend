package com.perrine.patientqueue.repository;

import com.perrine.patientqueue.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
