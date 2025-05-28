package com.example;

import java.util.Objects;

public final class Employer {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Integer password;
    private final Boolean flagged;

    private static final Employer INSTANCE = new Employer("Meiqing", "Shi", "mqshi@gmail.com", 123456, false);

    private Employer(String firstName, String lastName, String email, Integer password, Boolean flagged) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.flagged = flagged;
    }

    public static Employer getInstance() {
        return INSTANCE;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getPassword() {
        return password;
    }

    public Boolean getFlagged() {
        return flagged;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                ", flagged=" + flagged +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employer)) return false;
        Employer employee = (Employer) o;
        return Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(password, employee.password) &&
                Objects.equals(flagged, employee.flagged);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, password, flagged);
    }
}

