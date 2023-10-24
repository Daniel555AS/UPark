package co.edu.upb.upark;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

	

	private static String URL;
	private static String USER;
	private static String PASSWORD;
	private static String HOST;
	private static String PORT;
	private static String DATABASE;


	static {
		try (FileInputStream fis = new FileInputStream("config.properties")) {
			Properties props = new Properties();
			props.load(fis);
			HOST = props.getProperty("DB_HOST");
			PORT = props.getProperty("DB_PORT");
			DATABASE = props.getProperty("DB_NAME");
			USER = props.getProperty("DB_USERNAME");
			PASSWORD = props.getProperty("DB_PASSWORD");
			URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
		} catch (IOException e) {
			System.err.println("Error al cargar propiedades...");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
