package com.perrine.patientqueue.models;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "reason_admitted")
    private String reasonAdmitted;

    public Patient() {}

    public Patient(String firstName, String lastName, String reasonAdmitted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.reasonAdmitted = reasonAdmitted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getReasonAdmitted() {
        return reasonAdmitted;
    }

    public void setReasonAdmitted(String reasonAdmitted) {
        this.reasonAdmitted = reasonAdmitted;
    }

    public String getEnterMessage() {
        return String.format("%s %s has entered because of %s", firstName, lastName, reasonAdmitted);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", reasonAdmitted='" + reasonAdmitted + '\'' +
                '}';
    }
}
