package com.musiccollections;

import com.musiccollections.database.DatabaseConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class MusicCollectionsApplication {
    private String url;

    private String userName;

    private String password;

    public static void main(String[] args) {
        SpringApplication.run(MusicCollectionsApplication.class, args);
    }

    public void initializeDatabaseConnector() {
        try {
            DatabaseConnector databaseConnector = new DatabaseConnector(url, userName, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
