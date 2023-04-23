package com.perrine.hospitalrooms.models;

import java.util.Optional;

public class Room {
    private Patient patient;

    public Room(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Room{" +
                "patient=" + patient +
                '}';
    }
}
