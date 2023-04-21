package com.perrine.hospitalrooms.service;

import com.perrine.hospitalrooms.models.Floor;
import com.perrine.hospitalrooms.models.Patient;
import com.perrine.hospitalrooms.models.PatientList;
import com.perrine.hospitalrooms.models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomDirector {
    private Floor floor = new Floor(5);
    @Autowired
    private RestTemplate restTemplate;

    private List<Patient> getPatientQueue() {
        List<Patient> response = restTemplate
                .getForObject("http://localhost:8080/hospital/patients", PatientList.class).getPatients();
        floor.setPatients(response);
        return response;

    }

    public List<Patient> viewCurrentPatients() {
        List<Patient> patients = getPatientQueue();

        return patients;
    }

    public int availableRooms() {
        List<Patient> patients = getPatientQueue();
        return floor.getRooms().length - patients.size();
    }

    public Floor distributePatients() {
        List<Patient> patients = getPatientQueue();
        Room[] roomLayout = floor.getRooms();
        System.out.println(roomLayout.length);
        patients = getPatientQueue();
        int largerNum = roomLayout.length >= patients.size() ? roomLayout.length : patients.size();
        for(int i = 0; i < largerNum; i++) {
            System.out.println(largerNum + "larger num");
            System.out.println(patients.size());
            if(i < roomLayout.length) {
                System.out.println("less "+ i);
            Room room = new Room(Optional.of(patients.get(i)));
            roomLayout[i] = room;
            } else if(roomLayout.length < patients.size() ){
                floor.waitingRoom.add(patients.get(i));
            }
        }
        floor.setRooms(roomLayout);
        return floor;
    }

}