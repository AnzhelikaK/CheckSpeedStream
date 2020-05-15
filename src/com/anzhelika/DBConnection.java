package com.anzhelika;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;
    private DBConnection() { }

    public static Connection getConnection() {
        String userName = "userName";
        String password = "password";
        String connectionUrl="driver";
        try {
            connection= DriverManager.getConnection(connectionUrl, userName, password );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
