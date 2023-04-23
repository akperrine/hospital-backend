package com.perrine.hospitalrooms.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Floor {
    private HashMap<Integer,Room> rooms;
    private int availableRooms;
    private List<Patient> patients;
    public List<Patient> waitingRoom = new ArrayList<>();

    public Floor(int numberOfRooms) {
        this.availableRooms = numberOfRooms;
        this.rooms = initrooms(numberOfRooms);
    }

    private static HashMap<Integer, Room> initrooms(int numberOfRooms) {
        HashMap<Integer, Room> rooms = new HashMap<>();
        for(int i = 1; i <= numberOfRooms; i ++) {
            rooms.put(i, null);
        }
        return rooms;
    }


    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public HashMap<Integer, Room> getRooms() {
        return rooms;
    }

    public void setRooms(HashMap<Integer, Room> rooms) {
        this.rooms = rooms;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public List<Patient> getWaitingRoom() {
        return waitingRoom;
    }

    public void setWaitingRoom(List<Patient> waitingRoom) {
        this.waitingRoom = waitingRoom;
    }
}
