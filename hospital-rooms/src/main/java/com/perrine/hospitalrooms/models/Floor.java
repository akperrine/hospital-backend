package com.perrine.hospitalrooms.models;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private int rooms;
    private List<Patient> patients = new ArrayList<>();


    public Floor(int rooms) {
        this.rooms = rooms;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }


}
