package co.edu.upb.upark;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Exit extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String nameForExit = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Exit frame = new Exit();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Exit() {
		
		this.setResizable(false); // Disable the maximize window option
		
		String numberIdentification = Login.IdentificationNumber;
		String vehiclePlate = "";
		String vehicleInformation = "";

		// -------------------------------------------------- Get the Vehicle Information  --------------------------------------------------

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://:/", "***", "***");

			Statement stmt1 = conn.createStatement();
			String sql1 = "SELECT * FROM usuariosActuales WHERE numeroidentificacion = '" + numberIdentification + "'";
			ResultSet rs1 = stmt1.executeQuery(sql1);

			while (rs1.next()) {          
				vehiclePlate = rs1.getString("placa");
			}
			rs1.close();
			stmt1.close();

			Statement stmt2 = conn.createStatement();
			String sql2 = "SELECT * FROM vehiculos WHERE placa = '" + vehiclePlate + "'";
			ResultSet rs2 = stmt2.executeQuery(sql2);

			while (rs2.next()) {          
				String vehicleBrand = rs2.getString("marca");
				String vehicleColor = rs2.getString("color");
				String vehicleModel = rs2.getString("modelo");

				vehicleInformation = vehicleBrand + " " + vehiclePlate + " " + vehicleColor + " " + vehicleModel;
			}
			rs2.close();
			stmt2.close();
			conn.close();

		}
		catch(SQLException i){
			i.printStackTrace();
		}

		// ----------------------------------------------------------------------------------------------------------------------------------
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1286, 660);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Creation a JPanel for decorative purposes(The JPanel located at the bottom):
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 594, 1287, 29);
		contentPane.add(panel);

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://:/", "***", "***")) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery("SELECT NombreUsuario FROM usuariosActuales WHERE NumeroIdentificacion = '" + Login.IdentificationNumberExit + "'")) {
					if (resultSet.next()) {
						nameForExit = resultSet.getString("NombreUsuario");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Creation of a JLabel containing the text: "Confirmación De Datos Para Salida":
		JLabel lblConfirmationOfData = new JLabel("Confirmación De Datos Para Salida");
		lblConfirmationOfData.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmationOfData.setFont(new Font("Cambria", Font.BOLD, 66));
		lblConfirmationOfData.setBounds(63, 152, 1098, 88);
		contentPane.add(lblConfirmationOfData);

		// Creation of a JLabel with the Logo of the University:
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 10, 343, 132);
		lblNewLabel_1.setIcon(new ImageIcon("Media\\logo-upb-blanco1.png"));
		contentPane.add(lblNewLabel_1);

		// Confirmation Button Properties:
		JButton confirmButton = new JButton("CONFIRMAR");
		confirmButton.setBackground(new Color(255, 239, 91));
		confirmButton.setForeground(new Color(0, 0, 0));
		confirmButton.setFont(new Font("Cambria", Font.BOLD, 50));
		confirmButton.setBounds(826, 454, 335, 82);
		contentPane.add(confirmButton);

		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try (Connection connection = DriverManager.getConnection("jdbc:mysql://:/", "***", "***")) {
					try (Statement statement = connection.createStatement()) {
						String deleteQuery = "DELETE FROM usuariosActuales WHERE NumeroIdentificacion = '" + Login.IdentificationNumberExit + "'";
						statement.executeUpdate(deleteQuery);
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}

				JOptionPane optionPane = new JOptionPane("Salida confirmada. Gracias por usar nuestros servicios", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

				// Crear un JDialog sin botones
				JDialog dialog = new JDialog(Exit.this, "Confirmación", true);
				dialog.setContentPane(optionPane);

				// Establecer un temporizador para cerrar el diálogo después de 2 segundos
				Timer timer = new Timer(2000, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						dialog.dispose();
						// Volver a la ventana de login
						Login loginFrame = new Login();
						loginFrame.setVisible(true);
						dispose();
					}
				});

				timer.setRepeats(false);
				timer.start();

				dialog.pack();
				dialog.setLocationRelativeTo(Exit.this);
				dialog.setVisible(true);
			}
		});

		// Creation of a JLabel containing the User's Name:
		JLabel lblNameExit = new JLabel(nameForExit);
		lblNameExit.setForeground(new Color(0, 0, 0));
		lblNameExit.setFont(new Font("Cambria", Font.BOLD, 45));
		lblNameExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameExit.setBounds(63, 284, 1098, 49);
		contentPane.add(lblNameExit);

		//Boton para regresar (El color de fondo funciona solo al ejecutar)/Sin razon ..?
		JButton regresarButton = new JButton("REGRESAR");
		regresarButton.setBackground(new Color(255, 239, 91));
		regresarButton.setHorizontalAlignment(SwingConstants.CENTER);
		regresarButton.setForeground(new Color(0, 0, 0));
		regresarButton.setFont(new Font("Cambria", Font.BOLD, 50));
		regresarButton.setBounds(64, 454, 385, 82);
		contentPane.add(regresarButton);
		
		// Creation of a JLabel containing the vehicle Information:
		JLabel lblVehicleInformation = new JLabel(vehicleInformation);
		lblVehicleInformation.setForeground(new Color(75, 75, 75));
		lblVehicleInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblVehicleInformation.setFont(new Font("Cambria", Font.BOLD, 35));
		lblVehicleInformation.setBounds(74, 371, 1088, 59);
		contentPane.add(lblVehicleInformation);
		
		// Creation of a JLabel containing the text: "Vehículo:":
		JLabel lblNewLabel_3 = new JLabel("Vehículo:");
		lblNewLabel_3.setForeground(new Color(156, 18, 18));
		lblNewLabel_3.setFont(new Font("Cambria", Font.BOLD, 35));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(74, 343, 1088, 32);
		contentPane.add(lblNewLabel_3);

		regresarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login loginFrame = new Login();
				loginFrame.setVisible(true);  
				dispose();  
			}
		});
	}
}
