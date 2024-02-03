package com.example.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.*;

public class DbConnector {
    private static final Logger logger = LoggerFactory.getLogger(DbConnector.class);

    public static Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            logger.info("Successfully connected to database");
            return DriverManager.getConnection("jdbc:postgresql://172.17.0.1:5432/postgres", "postgres", "123");
        } catch (SQLException e) {
            logger.error("Error when connecting database", e);
            throw new RuntimeException(e);
        }
    }
}
