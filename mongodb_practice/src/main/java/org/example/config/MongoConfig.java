package org.example.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import org.example.model.Employee;

public class MongoConfig {

    private static final String DB_NAME = "mydb";
    private static final String CONNECTION_STRING = "mongodb://admin:admin@localhost:27017";
    private static final Datastore datastore;

    static {
        MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);
        datastore = Morphia.createDatastore(mongoClient, DB_NAME);
    }

    public static Datastore getDatastore() {
        return datastore;
    }
}

