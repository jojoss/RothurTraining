package com.example.ordersystem.service;

import com.example.ordersystem.model.LogEntry;
import com.example.ordersystem.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void log(String action) {
        LogEntry entry = new LogEntry();
        entry.setAction(action);
        entry.setTimestamp(Instant.now().toString());
        logRepository.save(entry);
    }
}
