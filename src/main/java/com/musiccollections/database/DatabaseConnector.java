package com.musiccollections.connection;

import java.sql.Connection;

public class DatabaseConnector {
    private static final String URL = System.getenv("DATABASE_URL");
    private static final String USER = System.getenv("POSTGRES_USER");
    private static final String PASSWORD = System.getenv("POSTGRES_PASSWORD");

    public static Connection connect(){
        
    }
}
