package edu.sdccd.cisc191.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:h2:./data/studentdb";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, "sa", "");
    }
}
 