package co.edu.upb.upark;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class LoginSecurity extends JFrame {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	
	private JPanel contentPane;
	private RoundedPasswordField passwordField;

	public static String name = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginSecurity frame = new LoginSecurity();
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
	
	public LoginSecurity() {

		this.setResizable(false); // Disable the maximize window option

		int positionLoginSecurity = Login.positionNumber;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1286, 660);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 594, 1287, 29);
		contentPane.add(panel);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 10, 343, 132);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("Media\\logo-upb-blanco1.png"));
		
		// Creation of a JLabel containing the text: "Interfaz de Seguridad":
		JLabel seguridadLabel = new JLabel("Interfaz de Seguridad");
		seguridadLabel.setHorizontalAlignment(SwingConstants.CENTER);
		seguridadLabel.setBounds(20, 143, 1224, 124);
		seguridadLabel.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 110));
		contentPane.add(seguridadLabel);
		
		// Creation of a JLabel containing the text: "Constraseña":
		JLabel contrasenaLabel = new JLabel("Contraseña");
		contrasenaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contrasenaLabel.setBounds(535, 299, 201, 39);
		contrasenaLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 30));
		contentPane.add(contrasenaLabel);

		passwordField = new RoundedPasswordField(15);
		passwordField.setBackground(new Color(237, 238, 223));
		passwordField.setBounds(440, 348, 392, 67);
		passwordField.setFont(new Font("Cambria", Font.BOLD, 35));
		contentPane.add(passwordField);
		
		// Creation of the button: "REGRESAR":
		RoundedButton regresarButton = new RoundedButton("REGRESAR", new Color(229, 86, 109), new Color(216, 58, 58), 1000);
		regresarButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 31));
		regresarButton.setForeground(new Color(0, 0, 0));
		regresarButton.setBackground(new Color(243, 37, 68));
		regresarButton.setBounds(10, 527, 221, 57);
		contentPane.add(regresarButton);
		regresarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Login ls = new Login();
				ls.setVisible(true);
				dispose(); //Close the current window

			}
		});

		// Creation of the button: "ACCEDER":
		RoundedButton accederButton = new RoundedButton("ACCEDER", new Color(255, 239, 91), new Color(247, 208, 57), 1000);
		accederButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 31));
		accederButton.setForeground(new Color(0, 0, 0));
		accederButton.setBackground(new Color(255, 239, 91));
		accederButton.setBounds(476, 446, 320, 57);
		contentPane.add(accederButton);
		accederButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] passwordVerification = passwordField.getPassword();
				
				if(passwordVerification.length == 0) {
					JOptionPane.showMessageDialog(null, "Debe ingresar su contraseña.", "ERROR - Campo Vacío", JOptionPane.ERROR_MESSAGE);
					passwordField.setText("");
				}
				else {

					char[] password = passwordField.getPassword();
					String passwordFinal = new String(password);

					try {
						Connection conn = DriverManager.getConnection("jdbc:mysql://:/", "***", "***");
						Statement stmtLoginSecurity = conn.createStatement();
						ResultSet rsLoginSecurity = stmtLoginSecurity.executeQuery("SELECT documento FROM usuarios");
						ArrayList<String> listLoginSecurity = new ArrayList<>();
						while (rsLoginSecurity.next()) {
							String valor = rsLoginSecurity.getString("documento");
							listLoginSecurity.add(valor);
						}
						String[] arrayLoginSecurity = listLoginSecurity.toArray(new String[0]);
						rsLoginSecurity.close();
						stmtLoginSecurity.close();

						Statement stmtLoginSecurityName = conn.createStatement();
						ResultSet rsLoginSecurityName = stmtLoginSecurityName.executeQuery("SELECT nombre FROM usuarios");
						ArrayList<String> listLoginSecurityName = new ArrayList<>();
						while (rsLoginSecurityName.next()) {
							String valor = rsLoginSecurityName.getString("nombre");
							listLoginSecurityName.add(valor);
						}
						String[] arrayLoginSecurityName = listLoginSecurityName.toArray(new String[0]);
						rsLoginSecurityName.close();
						stmtLoginSecurityName.close();

						if(arrayLoginSecurity[positionLoginSecurity].equals(passwordFinal)) {

							name = arrayLoginSecurityName[positionLoginSecurity];
							MenuSecurity m = new MenuSecurity();
							m.setVisible(true);
							dispose(); //Close the current window

						}//if

						else {
							JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "ERROR", JOptionPane.ERROR_MESSAGE);
							passwordField.setText("");
						}//else

						conn.close();

					}
					catch(SQLException i){
						i.printStackTrace();
					}//catch
					
				}//else

			}//public void actionPerformed(ActionEvent e)
		});

	} // public LoginSecurity()

} // public class LoginSecurity extends JFrame
