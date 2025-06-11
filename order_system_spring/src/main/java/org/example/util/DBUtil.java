package org.example.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {

    private static Connection connection;

    static {
        try {
            System.out.println("🔧 [DBUtil] Loading db.properties...");
            InputStream input = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");

            if (input == null) {
                System.err.println("❌ [DBUtil] db.properties not found in classpath!");
                throw new RuntimeException("db.properties not found");
            }

            Properties prop = new Properties();
            prop.load(input);

            String url = prop.getProperty("pg.url");
            String user = prop.getProperty("pg.user");
            String password = prop.getProperty("pg.password");

            System.out.println("✅ [DBUtil] Loaded properties:");
            System.out.println("   ➤ pg.url = " + url);
            System.out.println("   ➤ pg.user = " + user);
            System.out.println("   ➤ pg.password = " + (password == null || password.isEmpty() ? "(empty)" : "********"));

            System.out.println("🔌 [DBUtil] Attempting to connect to PostgreSQL...");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ [DBUtil] PostgreSQL connection established.");

        } catch (Exception e) {
            System.err.println("❌ [DBUtil] Failed to establish DB connection: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}