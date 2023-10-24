package co.edu.upb.upark;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DatabaseManager {
	private Connection conn;

	public DatabaseManager() {
		try {
			conn = DatabaseConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // public DatabaseManager()

	public String[] getIdNumberDataFromUsuarios() {
		String[] idNumberDataFromUsuarios = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT numeroidentificacion FROM usuarios");

			ArrayList<String> listIdNumberDataFromUsuarios = new ArrayList<>();
			while (rs.next()) {
				String valor = rs.getString("numeroidentificacion");
				listIdNumberDataFromUsuarios.add(valor);
			}
			idNumberDataFromUsuarios = listIdNumberDataFromUsuarios.toArray(new String[0]);
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idNumberDataFromUsuarios;
	} // public String[] getIdNumberDataFromUsuarios()

	public String[] getRolDataFromUsuarios() {
		String[] rolDataFromUsuarios = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT rol FROM usuarios");
			ArrayList<String> listRolDataFromUsuarios = new ArrayList<>();
			while (rs.next()) {
				String valor = rs.getString("rol");
				listRolDataFromUsuarios.add(valor);
			}
			rolDataFromUsuarios = listRolDataFromUsuarios.toArray(new String[0]);
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rolDataFromUsuarios;
	} // public String[] getRolDataFromUsuarios()

	public String[] getCurrentUserDataFromUsuariosActuales() {
		String[] currentUsersDataFromUsuariosActuales = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT NumeroIdentificacion FROM usuariosActuales");
			ArrayList<String> listCurrentUsersFromUsuariosActuales = new ArrayList<>();
			while (rs.next()) {
				String valor = rs.getString("NumeroIdentificacion");
				listCurrentUsersFromUsuariosActuales.add(valor);
			}
			currentUsersDataFromUsuariosActuales = listCurrentUsersFromUsuariosActuales.toArray(new String[0]);
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return currentUsersDataFromUsuariosActuales;
	} // public String[] getCurrentUserDataFromUsuariosActuales()

	public String[] getNamesFromUsuarios() {
		String[] namesFromUsuarios = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT nombre FROM usuarios");
			ArrayList<String> listNamesFromUsuarios = new ArrayList<>();
			while (rs.next()) {
				String valor = rs.getString("nombre");
				listNamesFromUsuarios.add(valor);
			}
			namesFromUsuarios = listNamesFromUsuarios.toArray(new String[0]);
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return namesFromUsuarios;
	} // public String[] getNamesFromUsuarios()

	public String[] getDocumentsFromUsuarios() {
		String[] documentsFromUsuarios = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT documento FROM usuarios");
			ArrayList<String> listDocumentsFromUsuarios = new ArrayList<>();
			while (rs.next()) {
				String valor = rs.getString("documento");
				listDocumentsFromUsuarios.add(valor);
			}
			documentsFromUsuarios = listDocumentsFromUsuarios.toArray(new String[0]);
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return documentsFromUsuarios;
	} // public String[] getDocumentsFromUsuarios()

	public int countRowsFromUsuariosActuales() {
		int rowCount = 0;
		try {
			String sql = "SELECT COUNT(*) FROM usuariosActuales";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			rowCount = rs.getInt(1);

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	} // public int countRowsFromUsuariosActuales()

	public String[] platesFromUsuariosActuales() {
		String[] platesFromUsuariosActuales = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT placa FROM usuariosActuales");
			ArrayList<String> listPlatesFromUsuariosActuales = new ArrayList<>();
			while (rs.next()) {
				String valor = rs.getString("placa");
				listPlatesFromUsuariosActuales.add(valor);
			}
			platesFromUsuariosActuales = listPlatesFromUsuariosActuales.toArray(new String[0]);
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return platesFromUsuariosActuales;
	} // public String[] platesFromUsuariosActuales()

	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // public void closeConnection()
} // public class DatabaseManager
