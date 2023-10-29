package com.musiccollections.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
    private Connection con;

    public DatabaseConnector(String url, String userName, String password) throws SQLException, ClassNotFoundException {
        this.con = this.createConnection(url, userName, password);
    }

    private Connection createConnection(String url, String userName, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Properties props = new Properties();
        props.setProperty("user", userName);
        props.setProperty("password", password);
        return DriverManager.getConnection(url, props);
    }

    public Connection getConnection() {
        return this.con;
    }
}

