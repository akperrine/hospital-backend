package com.perrine.hospitalrooms.models;

import java.util.Optional;

public class Room {
    private Optional<Patient> patient;

    public Room(Optional<Patient> patient) {
        this.patient = patient;
    }

    public Optional<Patient> getPatient() {
        return patient;
    }

    public void setPatient(Optional<Patient> patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Room{" +
                "patient=" + patient +
                '}';
    }
}
