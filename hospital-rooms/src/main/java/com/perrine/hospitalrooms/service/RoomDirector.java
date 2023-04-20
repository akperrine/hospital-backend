package com.perrine.hospitalrooms.service;

import com.perrine.hospitalrooms.models.Floor;
import com.perrine.hospitalrooms.models.Patient;
import com.perrine.hospitalrooms.models.PatientList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("") public class RoomDirector {
    private Floor floor = new Floor(5);
    private static List<Patient> patients = new ArrayList<>();

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/rooms")
    public PatientList movePatientsToRooms() {
        PatientList response = restTemplate.getForObject("http://localhost:8080/hospital/patients", PatientList.class);
        return response;
    }

}
