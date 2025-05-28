package com.example.postgresjdbc.model;

import java.util.List;
import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private String email;
    private Integer password;
    private Boolean flagged;
    private List<Integer> ids;

    public Employee() {}

    public Employee(String firstName, String lastName, String email, Integer password, Boolean flagged, List<Integer> ids) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.flagged = flagged;
        this.ids = ids;
    }

    // Getters
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

    public List<Integer> getIds() {
        return ids;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public void setFlagged(Boolean flagged) {
        this.flagged = flagged;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                ", flagged=" + flagged +
                ", ids=" + ids +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(password, employee.password) &&
                Objects.equals(flagged, employee.flagged) &&
                Objects.equals(ids, employee.ids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, password, flagged, ids);
    }
}
