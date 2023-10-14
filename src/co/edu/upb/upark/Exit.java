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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Exit extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String nameForExit = "";
	String hour, minutes, seconds, amOrPm;
	Calendar calendar;
	Thread thread1;
	private JLabel lblClock = new JLabel("");

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
		
		// Create a new thread and start it:
		thread1 = new Thread(this);
		thread1.start();
		
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
		lblConfirmationOfData.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 66));
		lblConfirmationOfData.setBounds(65, 152, 1141, 88);
		contentPane.add(lblConfirmationOfData);

		// Creation of a JLabel with the Logo of the University:
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 10, 343, 132);
		lblNewLabel_1.setIcon(new ImageIcon("Media\\logo-upb-blanco1.png"));
		contentPane.add(lblNewLabel_1);

		// Confirmation Button Properties:
		RoundedButton confirmButton = new RoundedButton("CONFIRMAR");
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
		lblNameExit.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 45));
		lblNameExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameExit.setBounds(38, 284, 1196, 49);
		contentPane.add(lblNameExit);

		//Boton para regresar (El color de fondo funciona solo al ejecutar)/Sin razon ..?
		RoundedButton regresarButton = new RoundedButton("REGRESAR");
		regresarButton.setBackground(new Color(255, 239, 91));
		regresarButton.setHorizontalAlignment(SwingConstants.CENTER);
		regresarButton.setForeground(new Color(0, 0, 0));
		regresarButton.setFont(new Font("Cambria", Font.BOLD, 50));
		regresarButton.setBounds(64, 454, 385, 82);
		contentPane.add(regresarButton);
		
		// Creation of a JLabel containing the vehicle Information:
		JLabel lblVehicleInformation = new JLabel(vehicleInformation);
		lblVehicleInformation.setForeground(new Color(104, 104, 104));
		lblVehicleInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblVehicleInformation.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 35));
		lblVehicleInformation.setBounds(74, 371, 1088, 59);
		contentPane.add(lblVehicleInformation);
		
		// Creation of a JLabel containing the text: "Vehículo:":
		JLabel lblNewLabel_3 = new JLabel("Vehículo:");
		lblNewLabel_3.setForeground(new Color(229, 86, 109));
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
		
		// Properties of the JLabel containing the Current Time:
		lblClock.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 52));
		lblClock.setHorizontalAlignment(SwingConstants.CENTER);
		lblClock.setBounds(868, 42, 370, 57);
		contentPane.add(lblClock);
		
		// Creation of a JLabel containing the text: "Hora:":
		JLabel lblHour = new JLabel("Hora:");
		lblHour.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 54));
		lblHour.setHorizontalAlignment(SwingConstants.LEFT);
		lblHour.setBounds(730, 42, 148, 59);
		contentPane.add(lblHour);
		
	} // public Exit()
	
	@Override
	public void run() {

		Thread currentThread = Thread.currentThread();

		while(currentThread == thread1) {

			calculate();
			lblClock.setText(hour + ":" + minutes + ":" + seconds + "  " + amOrPm);
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {}

		} // while(currentThread == thread1)

	} // public void run()

	private void calculate() {

		Calendar calendar = new GregorianCalendar();
		Date currentTime = new Date();

		calendar.setTime(currentTime);
		amOrPm = calendar.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";

		if(amOrPm.equals("PM")) {
			int h = calendar.get(Calendar.HOUR_OF_DAY)-12;
			hour = h>9?""+h:"0"+h;
		}
		else {
			hour = calendar.get(Calendar.HOUR_OF_DAY)>9?""+calendar.get(Calendar.HOUR_OF_DAY):"0"+calendar.get(Calendar.HOUR);
		}

		minutes = calendar.get(Calendar.MINUTE)>9?""+calendar.get(Calendar.MINUTE):"0"+calendar.get(Calendar.MINUTE);
		seconds = calendar.get(Calendar.SECOND)>9?""+calendar.get(Calendar.SECOND):"0"+calendar.get(Calendar.SECOND);

	} // private void calculate()
	
} // public class Exit extends JFrame implements Runnable
