package com.perrine.hospitalrooms.models;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private Room[] rooms;

    private List<Patient> patients;
    public List<Patient> waitingRoom = new ArrayList<>();

    public Floor(int amountOfRooms) {
        this.rooms = new Room[amountOfRooms];
    }

    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
