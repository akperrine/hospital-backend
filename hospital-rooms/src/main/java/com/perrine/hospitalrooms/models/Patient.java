package com.perrine.hospitalrooms.models;


public class Patient {
        private long id;

        private String firstName;

        private String lastName;

        private String reasonAdmitted;

        public Patient() {}

        public Patient(String firstName, String lastName, String reasonAdmitted) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.reasonAdmitted = reasonAdmitted;
        }

        public String getEnteredOrExitMessage(String enterOrExit) {
            return String.format("%s %s has %s because of %s", firstName, lastName, enterOrExit, reasonAdmitted);
        }

        // Getters and Setters

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
