package com.perrine.patientqueue.services;

import com.perrine.patientqueue.config.KafkaProducer;
import com.perrine.patientqueue.models.Patient;
import com.perrine.patientqueue.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @Transactional
    public void patientEnters(Patient patient) {
        kafkaProducer.sendData(patient);
        patientRepository.save(patient);
    }

    public Patient getPatientById(Long id) {
        Patient patient = patientRepository.findById(id).get();
        kafkaProducer.sendData(patient);
        return patient;
    }

    public void removePatient(Long id) {
        patientRepository.deleteById(id);
    }

}
