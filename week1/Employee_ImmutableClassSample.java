package com.example;

import java.util.Objects;

public final class Employee {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Integer password;
    private final Boolean flagged;

    Employee(String firstName, String lastName, String email, Integer password, Boolean flagged) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.flagged = flagged;
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
        return "Employee{" +
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
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getFirstName(), employee.getFirstName()) && Objects.equals(getLastName(), employee.getLastName()) && Objects.equals(getEmail(), employee.getEmail()) && Objects.equals(getPassword(), employee.getPassword()) && Objects.equals(getFlagged(), employee.getFlagged());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getEmail(), getPassword(), getFlagged());
    }
}
