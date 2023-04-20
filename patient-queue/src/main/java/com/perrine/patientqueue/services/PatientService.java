package com.perrine.patientqueue.services;

import com.perrine.patientqueue.models.Patient;
import com.perrine.patientqueue.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public void patientEnters(Patient patient) {
        patientRepository.save(patient);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).get();
    }

    public void removePatient(Long id) {
        patientRepository.deleteById(id);
    }

}
