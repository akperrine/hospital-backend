package com.perrine.patientqueue.controllers;

import com.perrine.patientqueue.config.KafkaProducer;
import com.perrine.patientqueue.models.Patient;
import com.perrine.patientqueue.models.PatientList;
import com.perrine.patientqueue.repository.PatientRepository;
import com.perrine.patientqueue.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hospital")
public class PatientController {
    @Autowired
    PatientService patientService;

//    @Autowired
//    KafkaProducer kafkaProducer;

    @GetMapping("/patients")
    public ResponseEntity<PatientList> getAllPatients(){
        try {
            List<Patient> patients = patientService.getPatients();
            PatientList patientList = new PatientList();
            patientList.setPatients(patients);
            return new ResponseEntity<>(patientList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> get(@PathVariable Long id) {
        try {
            Patient patient = patientService.getPatientById(id);
            return new ResponseEntity<Patient>(patient, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/patients")
    public ResponseEntity<Patient> signInPatient(@RequestBody Patient patient) {
        try {
            patientService.patientEnters(patient);
            return new ResponseEntity<>(patient, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("patients/{id}")
    public long delete(@PathVariable Long id) {
        patientService.removePatient(id);

        return id;
    }
}
