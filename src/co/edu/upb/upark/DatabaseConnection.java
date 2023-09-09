package co.edu.upb.upark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
    private static final String URL = "jdbc:mysql://:/";
    private static final String USER = "***";
    private static final String PASSWORD = "***";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
