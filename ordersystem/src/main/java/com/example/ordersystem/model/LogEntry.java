package com.example.ordersystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "log_entries")
public class LogEntry {

    @Id
    private String id;

    private String action;
    private String timestamp;

    // 无参构造函数
    public LogEntry() {}

    // 有参构造函数
    public LogEntry(String action, String timestamp) {
        this.action = action;
        this.timestamp = timestamp;
    }

    // Getter 和 Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    // 可选：重写 toString 方法便于调试
    @Override
    public String toString() {
        return "LogEntry{" +
                "id='" + id + '\'' +
                ", action='" + action + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
