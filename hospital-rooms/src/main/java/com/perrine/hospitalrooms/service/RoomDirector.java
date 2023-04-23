package com.perrine.hospitalrooms.service;

import com.perrine.hospitalrooms.models.Floor;
import com.perrine.hospitalrooms.models.Patient;
import com.perrine.hospitalrooms.models.PatientList;
import com.perrine.hospitalrooms.models.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Service
public class RoomDirector {
    private Floor floor = new Floor(5);
    private int numberOfRooms;
    private List<Patient> waitingRoom;

    private static final Logger logger = LoggerFactory.getLogger(RoomDirector.class);

    @Autowired
    private RestTemplate restTemplate;

    private List<Patient> getPatientQueue() {
        List<Patient> response = restTemplate
                .getForObject("http://patient-queue/hospital/patients", PatientList.class).getPatients();
        floor.setPatients(response);
        return response;

    }

    public boolean checkPatientListInitialized() {
        boolean initialized = floor.getPatients() == null ? false : true;
        return initialized;
    }

    public List<Patient> viewCurrentPatients() {
        List<Patient> patients = getPatientQueue();

        return patients;
    }

    public int availableRooms() {
        return floor.getAvailableRooms();
    }

    public Floor distributePatients() {
        logger.info(String.format("Initial Update from Patient Queue microservice"));
        List<Patient> patients = getPatientQueue();
        HashMap<Integer, Room> roomLayout = floor.getRooms();
        for(int i = 1; i <= floor.getRooms().size(); i++) {
            Room room = new Room(null);
            roomLayout.put(i, room);
        }
        for(int i = 0; i < patients.size(); i++) {
            if(i < roomLayout.size()) {
            Room room = new Room(patients.get(i));
            roomLayout.put(i + 1, room);
            int roomsAvailable = floor.getAvailableRooms();
            floor.setAvailableRooms(roomsAvailable - 1);
            } else{
                floor.waitingRoom.add(patients.get(i));
            }
        }
        numberOfRooms = floor.getRooms().size();
        floor.setRooms(roomLayout);
        return floor;
    }

    public Floor updateRoomDistribution() {
        List<Patient> patients = floor.getPatients();
        List<Patient> updatedPatients = getPatientQueue();
        this.waitingRoom = floor.getWaitingRoom();

            // This assumes that things update with every single change;
        if(patients.size() == updatedPatients.size()) {
            logger.info(String.format("Nothing to update"));
            return floor;
        } else if(patients.size() < updatedPatients.size()) {
            logger.info(String.format("Patient Entered"));
            updatePatientEntered(waitingRoom, updatedPatients);
        } else if(patients.size() > updatedPatients.size()){
            logger.info(String.format("Patient Exited"));
            updatePatientExits(patients, updatedPatients);
        }
        return floor;
    }

       private Floor updatePatientEntered(List<Patient> waitingRoom, List<Patient> updatedPatients) {
           HashMap<Integer, Room> rooms = floor.getRooms();
        // Search through rooms to see if any empty
           Patient latestPatientToEnter = updatedPatients.get(updatedPatients.size() - 1);
           for(int i = 1; i <= numberOfRooms; i++) {
               if (rooms.get(i).getPatient() == null) {
                   if (waitingRoom.size() > 0) {
                       rooms.get(i).setPatient(waitingRoom.get(0));
                       waitingRoom.remove(0);
                       waitingRoom.add(latestPatientToEnter);
                       floor.setPatients(updatedPatients);
                   } else {
                       rooms.get(i).setPatient(latestPatientToEnter);
                       floor.setPatients(updatedPatients);
                   }
                   return floor;
               }
           }
           // If not empty, adds new patient to waiting room
           waitingRoom.add(latestPatientToEnter);
           floor.setPatients(updatedPatients);
               return floor;
       }

       private Floor updatePatientExits(List<Patient> patients, List<Patient> updatedPatients) {
           HashMap<Integer, Room> rooms = floor.getRooms();
           Patient exitPerson = findDeletedPerson(patients, updatedPatients);

         Room vacantRoom;
           System.out.println(exitPerson);
           for(int i = 1; i <= numberOfRooms; i++) {
               Patient getPatientOrEmpty = rooms.get(i).getPatient();
               if(getPatientOrEmpty != null) {
                   if (exitPerson.getId() == getPatientOrEmpty.getId()) {
                       vacantRoom = rooms.get(i);
                       if (waitingRoom.size() > 0) {
                           vacantRoom.setPatient(waitingRoom.get(0));
                           waitingRoom.remove(0);
                       } else {
                           vacantRoom.setPatient(null);
                       }
                       floor.setPatients(updatedPatients);
                       return floor;
                   }
               } else {
                   return floor;
               }

           }
           for(int i = 0; i < waitingRoom.size(); i++) {
               if(waitingRoom.get(i).getId() == exitPerson.getId()) {
                   waitingRoom.remove(i);
                   floor.setPatients(updatedPatients);
               }
           }
           return floor;
       }

       private Patient findDeletedPerson(List<Patient> patients, List<Patient> updatedPatients) {
           Patient deletedPatient;
           HashMap<Long, Integer> idMap = new HashMap<>();
           for(int i = 0; i < updatedPatients.size(); i++) {
               idMap.put(updatedPatients.get(i).getId(), i);
           }
           for(int i = 0; i < patients.size(); i++) {
               long id = patients.get(i).getId();
               if(idMap.get(id) == null) {
                   deletedPatient = patients.get(i);
                   return deletedPatient;
               }
           }
           return null;
       }

}