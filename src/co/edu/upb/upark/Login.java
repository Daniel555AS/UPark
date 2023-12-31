package co.edu.upb.upark;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.*;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements Runnable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	private JPanel contentPane;
	private JTextField textField;
	public static int positionNumber;
	public static int positionNumberExit;
	public static String IdentificationNumberExit;
	public static String IdentificationNumber;

	String hour, minutes, seconds, amOrPm;
	Calendar calendar;
	Thread thread1;
	private JLabel lblClock = new JLabel("");
	private DatabaseManager databaseManager;
	private SoundPlayer soundPlayer = new SoundPlayer();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {

		this.setResizable(false); // Disable the maximize window option

		// Create a new thread and start it:
		thread1 = new Thread(this);
		thread1.start();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the operation that will be performed when the window is closed
		setBounds(100, 100, 1286, 660); // Size and position of the frame

		// Creating a new JPanel and setting its properties:
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane); // Set the content pane of the frame to the new JPanel
		contentPane.setLayout(null); // Set the layout of the panel to null

		// Creation of the black JPanel, located at the bottom of the window:
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 594, 1287, 29);
		contentPane.add(panel_1);

		// Creation of a JLabel containing the text: "UPARK".
		JLabel uParkLabel = new JLabel("UPARK");
		uParkLabel.setHorizontalAlignment(SwingConstants.CENTER);
		uParkLabel.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 150));
		uParkLabel.setBounds(376, 135, 519, 124);
		contentPane.add(uParkLabel);

		/*
		 * Creation of the field where the data corresponding to the user can be entered:
		 * UPB User --> ID.
		 * Visitor --> Document No.
		 */
		textField = new RoundedTextField(10);
		textField.setBackground(new Color(237, 238, 223));
		textField.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 28));
		textField.setBounds(440, 348, 392, 67);
		contentPane.add(textField);
		textField.setColumns(10);

		// Creation of a JLabel containing the text: "Usuario":
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 30));
		lblNewLabel_1.setBounds(578, 299, 116, 39);
		contentPane.add(lblNewLabel_1);

		// Creation of the button: "ACCEDER":
		RoundedButton accederButton = new RoundedButton("ACCEDER", new Color(255, 239, 91), new Color(247, 208, 57), 1000);
		accederButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 31));
		accederButton.setForeground(new Color(0, 0, 0));
		accederButton.setBackground(new Color(255, 239, 91));
		accederButton.setBounds(476, 483, 320, 57);
		accederButton.setFocusable(false);
		contentPane.add(accederButton);

		databaseManager = new DatabaseManager(); // Instance of the public class DatabaseManager

		accederButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String text = textField.getText().trim(); 

				if(text.equals("") || text.length() == 0) {
					soundPlayer.playSound("Media\\ErrorSound.wav");
					JOptionPane.showMessageDialog(null, "Debe ingresar su número ID.", "ERROR - Campo Vacío", JOptionPane.ERROR_MESSAGE);
					textField.setText("");				
				} 

				else {
					String[] idNumberDataFromUsuarios = databaseManager.getIdNumberDataFromUsuarios();
					String[] idNumberDataFromUsuariosActuales = databaseManager.getCurrentUserDataFromUsuariosActuales();
					String[] rolDataFromUsuarios = databaseManager.getRolDataFromUsuarios();

					int counter = 0;
					int counterCurrentUsers = 0;

					for(int ii = 0; ii < idNumberDataFromUsuarios.length; ii++) {
						if(textField.getText().equals(idNumberDataFromUsuarios[ii])) {
							counter++;
							positionNumber = ii;
							IdentificationNumber = idNumberDataFromUsuarios[ii];
						}
					}

					for(int jj = 0; jj < idNumberDataFromUsuariosActuales.length ; jj++) {
						if(textField.getText().equals(idNumberDataFromUsuariosActuales[jj]) == true) {
							counterCurrentUsers++;
							positionNumberExit = jj;
							IdentificationNumberExit = idNumberDataFromUsuariosActuales[jj];
						}
					}

					if(counterCurrentUsers > 0) {
						dispose();

						//Display Exit exitFrame
						soundPlayer.playSound("Media\\AccessSound.wav");
						Exit exitFrame = new Exit();
						exitFrame.setVisible(true);
						databaseManager.closeConnection();
					} // if(counterCurrentUser > 0)
					else {
						if(counter > 0) {
							if(rolDataFromUsuarios[positionNumber].equals("Vigilante")) {
								
								soundPlayer.playSound("Media\\AccessSound.wav");
								LoginSecurity l = new LoginSecurity();
								l.setVisible(true);
								dispose(); //Close the current window
							}// Security.
							else {		
								if(databaseManager.countRowsFromUsuariosActuales() == 15) {
									soundPlayer.playSound("Media\\ErrorSound.wav");
									JOptionPane.showMessageDialog(null, "Los cupos del parqueadero han alcanzado su límite.", "ERROR-CUPOS-LÍMITE", JOptionPane.ERROR_MESSAGE);
									textField.setText("");
								}
								else {
									soundPlayer.playSound("Media\\AccessSound.wav");
									SelectCar p = new SelectCar();
									p.setVisible(true);
									dispose(); //Close the current window
								}
							}// Students, Teachers and others.
						}// if(counter > 0)

						else {
							soundPlayer.playSound("Media\\ErrorSound.wav");
							JOptionPane.showMessageDialog(null, "El usuario no ha sido encontrado.", "ERROR", JOptionPane.ERROR_MESSAGE);
							textField.setText("");
						} 
					}	
				}

			} // public void actionPerformed(ActionEvent e)

		});
				
		//255, 239, 91

		// Creation of a JLabel with the Logo of the University:
		JLabel lblUpbLogo = new JLabel("");
		lblUpbLogo.setBounds(10, 10, 343, 132);
		contentPane.add(lblUpbLogo);
		lblUpbLogo.setIcon(new ImageIcon("Media\\logo-upb-blanco1.png"));

		// Properties of the JLabel containing the Current Time:
		lblClock.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 52));
		lblClock.setHorizontalAlignment(SwingConstants.CENTER);
		lblClock.setBounds(868, 42, 370, 59);
		contentPane.add(lblClock);

		// Creation of a JLabel containing the text: "Hora:":
		JLabel lblHour = new JLabel("Hora:");
		lblHour.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 60));
		lblHour.setHorizontalAlignment(SwingConstants.LEFT);
		lblHour.setBounds(731, 42, 148, 59);
		contentPane.add(lblHour);

		// Creation of a JLabel containing the text: "Puestos Ocupados:":
		JLabel lblOccupiedPositionsText = new JLabel("Puestos Ocupados:");
		lblOccupiedPositionsText.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 45));
		lblOccupiedPositionsText.setBounds(24, 434, 397, 57);
		contentPane.add(lblOccupiedPositionsText);

		JLabel lblOccupiedPositionsNumber = new JLabel(databaseManager.countRowsFromUsuariosActuales() + "/15");
		lblOccupiedPositionsNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblOccupiedPositionsNumber.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 45));
		lblOccupiedPositionsNumber.setBounds(24, 483, 397, 57);
		contentPane.add(lblOccupiedPositionsNumber);
		
		// // Creation of the Button: "OBSERVAR MAPA":
		RoundedButton observarMapaButton= new RoundedButton("OBSERVAR MAPA", new Color(255, 255, 255),new Color(196, 193, 186), 1000);
		observarMapaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soundPlayer.playSound("Media\\ButtonSound.wav");
				Map map = new Map();
				map.setVisible(true);
				dispose(); //Close the current window
			}
		});
		observarMapaButton.setBackground(new Color(255, 255, 255));
		observarMapaButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 31));
		observarMapaButton.setForeground(new Color(0, 0, 0));
		observarMapaButton.setBounds(918, 510, 320, 57);
		observarMapaButton.setFocusable(false);
		contentPane.add(observarMapaButton);
		
		
		// Creation of a RoundedPanel that works as a frame of the observarMapaButton:
		RoundedPanel panel = new RoundedPanel(20);
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(915, 506, 326, 64);
		contentPane.add(panel);

	} // public Login()


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
}
