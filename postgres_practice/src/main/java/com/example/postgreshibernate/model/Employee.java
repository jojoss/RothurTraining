package com.example.postgreshibernatenate.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private Integer password;

    @Column(name = "flagged")
    private Boolean flagged;

    public Employee() {}

    public Employee(String email, String firstName, String lastName, Integer password, Boolean flagged) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.flagged = flagged;
    }

    // Getters and setters...

    @Override
    public String toString() {
        return "Employee{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password=" + password +
                ", flagged=" + flagged +
                '}';
    }
}