package com.perrine.hospitalrooms.controller;

import com.perrine.hospitalrooms.models.Floor;
import com.perrine.hospitalrooms.models.Patient;
import com.perrine.hospitalrooms.models.PatientList;
import com.perrine.hospitalrooms.models.Room;
import com.perrine.hospitalrooms.service.RoomDirector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RoomDirector roomDirector;

    @GetMapping
    public List<Patient> checkPatientList(){
        List<Patient> patients = roomDirector.viewCurrentPatients();
        return patients;
    }

    @GetMapping("place")
    public Floor checkAvailable(){
      if(!roomDirector.checkPatientListInitialized()) {
        return roomDirector.distributePatients();
      }
      else {
          return roomDirector.updateRoomDistribution();
      }
    }
}
