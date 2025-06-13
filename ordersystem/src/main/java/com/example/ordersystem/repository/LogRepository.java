package com.example.ordersystem.repository;

import com.example.ordersystem.model.LogEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<LogEntry, String> {
}
