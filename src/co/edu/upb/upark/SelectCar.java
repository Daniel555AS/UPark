package co.edu.upb.upark;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

public class SelectCar extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	
	private JPanel contentPane;
	private static String name = "";
	private static String doc = "";
	private static String[] vehicleInformation1 = new String[5];
	private static String[] vehicleInformation2 = new String[5];
	private static String[]  vehicles = new String[2];
	public static String selectedVehicle;
	
	String hour, minutes, seconds, amOrPm;
	Calendar calendar;
	Thread thread1;
	private JLabel lblClock = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectCar frame = new SelectCar();
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
	public SelectCar() {

		this.setResizable(false); // Disable the maximize window option
		
		thread1 = new Thread(this);
		thread1.start();

		int numberPosition = Login.positionNumber;
		String numberIdentification = Login.IdentificationNumber;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1286, 660);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 594, 1287, 29);
		panel.setBackground(new Color(0, 0, 0));
		contentPane.add(panel);
		
		
		// Creation of a JLabel with the Logo of the University:
		JLabel lblUpbLogo = new JLabel("");
		lblUpbLogo.setBounds(10, 10, 343, 132);
		contentPane.add(lblUpbLogo);
		lblUpbLogo.setIcon(new ImageIcon("Media\\logo-upb-blanco1.png"));

		
		// Creation of a JLabel with the text: "Escoge Tu Vehículo":
		JLabel lblNewLabel_1 = new JLabel("Escoge Tu Vehículo");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 99));
		lblNewLabel_1.setBounds(126, 124, 1020, 124);
		contentPane.add(lblNewLabel_1);

		int countVehicle1 = 0;
		int countVehicle2 = 0;

		// --------------------- Get the name of the vehicle owner, the document of the vehicle owner and the array of vehicles ---------------------

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://:/", "***", "***");
			Statement stmt1 = conn.createStatement();
			ResultSet rs = stmt1.executeQuery("SELECT nombre FROM usuarios");
			ArrayList<String> listSelectCar1 = new ArrayList<>();
			while (rs.next()) {
				String valor = rs.getString("nombre");
				listSelectCar1.add(valor);
			}
			String[] arraySelectCar1 = listSelectCar1.toArray(new String[0]);
			rs.close();
			stmt1.close();

			name = arraySelectCar1[numberPosition];	

			Statement stmtDoc = conn.createStatement();
			ResultSet rsDoc = stmtDoc.executeQuery("SELECT documento FROM usuarios");
			ArrayList<String> listDoc = new ArrayList<>();
			while (rsDoc.next()) {
				String valor = rsDoc.getString("documento");
				listDoc.add(valor);
			}
			String[] arrayDoc = listDoc.toArray(new String[0]);
			rsDoc.close();
			stmtDoc.close();

			doc = arrayDoc[numberPosition];

			Statement stmt3 = conn.createStatement();
			String query = "SELECT * FROM vehiculos WHERE numeroidentificacion = '" + numberIdentification + "'"; 
			ResultSet rs3 = stmt3.executeQuery(query);
			String rowString1 = "";
			String rowString2 = "";
			int count = 0;
			while (rs3.next()) {
				count++;
				if (count == 1) {
					for (int jj = 1; jj <= rs3.getMetaData().getColumnCount(); jj++) {
						String rowString= "";
						rowString = rs3.getString(jj);
						vehicleInformation1[countVehicle1] = rowString;
						countVehicle1++;

					}
					for (int j = 1; j < rs3.getMetaData().getColumnCount(); j++) {

						rowString1 += rs3.getString(j) + " ";

					}

				} else if (count == 2) {
					for (int jj = 1; jj <= rs3.getMetaData().getColumnCount(); jj++) {
						String rowString = "";
						rowString = rs3.getString(jj);
						vehicleInformation2[countVehicle2] = rowString;
						countVehicle2++;
					}

					for (int j = 1; j < rs3.getMetaData().getColumnCount(); j++) {

						rowString2 += rs3.getString(j) + " ";

					}
				}
			}

			vehicles[0] = rowString1;
			vehicles[1] = rowString2;

			rs3.close();
			stmt3.close();

			conn.close();
		}
		catch(SQLException i){
			i.printStackTrace();
		}

		//------------------------------------------------------------------------------------------------------------------------------ ------------
		
		
		// Creation of a JLabel with the user's name as text:
		JLabel lblNewLabel_2 = new JLabel(name);
		lblNewLabel_2.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 37));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(33, 258, 1206, 49);
		contentPane.add(lblNewLabel_2);

		JComboBox<String> comboBox = new JComboBox<String>();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		if(vehicles[0] != "") {
			model.addElement(vehicles[0]);
		}
		if(vehicles[1] != "") {
			model.addElement(vehicles[1]);
		}

		comboBox.setModel(model);
		comboBox.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 30));
		comboBox.setBounds(216, 345, 840, 71);
		contentPane.add(comboBox);

		RoundedButton btnNewButton = new RoundedButton("INGRESAR");
		btnNewButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 31));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 239, 91));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// close SelectCar
				dispose();

				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://35.222.147.13:3306/parqueadero", "root", "842963");

					String query = "INSERT INTO usuariosActuales (numeroidentificacion, nombreusuario, documentousuario, placa) VALUES (?, ?, ?, ?)";
					PreparedStatement preparedStatement = conn.prepareStatement(query);

					String identificationNumberUser = Login.IdentificationNumber;

					String LicensePlate = "";

					if((String) comboBox.getSelectedItem() == vehicles[0]) {
						LicensePlate = vehicleInformation1[1];
						selectedVehicle = vehicles[0];
					}

					else {
						LicensePlate = vehicleInformation2[1];
						selectedVehicle = vehicles[1];
					}

					preparedStatement.setString(1, identificationNumberUser);
					preparedStatement.setString(2, name);
					preparedStatement.setString(3, doc);
					preparedStatement.setString(4, LicensePlate);

					preparedStatement.executeUpdate();

					conn.close();

				}// try

				catch(SQLException i){
					i.printStackTrace();
				}// catch		        

				// display Success
				Success s = new Success();
				s.setVisible(true);

				// Swing Timer with a 5-second delay
				Timer timer = new Timer(5000, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// close Success s
						s.dispose();

						// display Login
						Login l = new Login();
						l.setVisible(true);
					}
				});

				timer.setRepeats(false);
				timer.start(); // start the timer
			}
		});
		btnNewButton.setBounds(476, 483, 320, 57);
		//btnNewButton.setBounds(416, 468, 440, 76);)
		contentPane.add(btnNewButton);
		
		/////////////////////////////////
		
		btnNewButton.addMouseListener(new MouseAdapter() {
		    private Timer timer;
		    private int alpha = 0;
		    private Color startColor = new Color(255, 239, 91);
		    private Color endColor = new Color(247, 208, 57);
		    private int animationDuration = 1000; // In milliseconds

		    @Override
		    public void mouseEntered(MouseEvent e) {
		        if (timer != null && timer.isRunning()) {
		            timer.stop();
		        }
		        alpha = 0;
		        timer = new Timer(animationDuration / 100, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                alpha += 10;
		                if (alpha >= 255) {
		                    alpha = 255;
		                    timer.stop();
		                }
		                Color color = new Color(
		                        (int) (startColor.getRed() * (1 - alpha / 255.0) + endColor.getRed() * (alpha / 255.0)),
		                        (int) (startColor.getGreen() * (1 - alpha / 255.0) + endColor.getGreen() * (alpha / 255.0)),
		                        (int) (startColor.getBlue() * (1 - alpha / 255.0) + endColor.getBlue() * (alpha / 255.0))
		                );
		                btnNewButton.setBackground(color);
		            }
		        });
		        timer.start();
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        if (timer != null && timer.isRunning()) {
		            timer.stop();
		        }
		        alpha = 0;
		        timer = new Timer(animationDuration / 100, new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                alpha += 10;
		                if (alpha >= 255) {
		                    alpha = 255;
		                    timer.stop();
		                }
		                Color color = new Color(
		                        (int) (endColor.getRed() * (1 - alpha / 255.0) + startColor.getRed() * (alpha / 255.0)),
		                        (int) (endColor.getGreen() * (1 - alpha / 255.0) + startColor.getGreen() * (alpha / 255.0)),
		                        (int) (endColor.getBlue() * (1 - alpha / 255.0) + startColor.getBlue() * (alpha / 255.0))
		                );
		                btnNewButton.setBackground(color);
		            }
		        });
		        timer.start();
		    }
		}); // btnNewButton.addMouseListener(new MouseAdapter()
		
		
		
		/////////////////////////////////
		
		
		lblClock.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 52));
		lblClock.setHorizontalAlignment(SwingConstants.CENTER);
		lblClock.setBounds(868, 42, 370, 59);
		contentPane.add(lblClock);
		
		
		// Creation of a JLabel with the text: "Hora":
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 60));
		lblHora.setHorizontalAlignment(SwingConstants.LEFT);
		lblHora.setBounds(730, 42, 148, 59);
		contentPane.add(lblHora);
		
		
	} // public SelectCar()

	@Override
	public void run() {
		
		Thread currentThread = Thread.currentThread();
		
		while(currentThread == thread1) {
			
			calculate();
			lblClock.setText(hour + ":" + minutes + ":" + seconds + "  " + amOrPm);
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {}
			
		}//while(currentThread == thread1)
		
	}// public void run()

	
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


} // public class SelectCar extends JFrame implements Runnable
