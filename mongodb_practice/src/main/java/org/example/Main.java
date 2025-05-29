package org.example;

import org.example.config.MongoConfig;
import org.example.model.Employee;
import dev.morphia.Datastore;

public class Main {
    public static void main(String[] args) {
        Datastore datastore = MongoConfig.getDatastore();

        Employee emp = new Employee();
        emp.setFirstName("John");
        emp.setLastName("Doe");
        emp.setEmail("john.doe@example.com");

        datastore.save(emp);
        System.out.println("Employee saved to MongoDB.");
    }
}