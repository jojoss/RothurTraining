package com.example.postgresjdbc.model;

public class Department {
    private int id;
    private String name;
    private String employeeEmail;

    public Department(int id, String name, String employeeEmail) {
        this.id = id;
        this.name = name;
        this.employeeEmail = employeeEmail;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                '}';
    }
}