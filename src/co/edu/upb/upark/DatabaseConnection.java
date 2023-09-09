package co.edu.upb.upark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private static final String URL = "jdbc:mysql://35.222.147.13:3306/parqueadero";
    private static final String USER = "root";
    private static final String PASSWORD = "842963";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
