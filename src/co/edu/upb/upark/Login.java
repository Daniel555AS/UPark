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
//
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//

import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public static int positionNumber;
	public static String IdentificationNumber;
	
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

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1286, 660);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 594, 1287, 29);
		contentPane.add(panel_1);

		JLabel lblNewLabel = new JLabel("UPARK");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("HP Simplified", Font.BOLD, 99));
		lblNewLabel.setBounds(376, 135, 519, 124);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBackground(new Color(237, 238, 223));
		textField.setFont(new Font("Tahoma", Font.BOLD, 25));
		textField.setBounds(440, 348, 392, 67);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setFont(new Font("HP Simplified", Font.BOLD, 30));
		lblNewLabel_1.setBounds(578, 299, 116, 39);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("ACCEDER");
		btnNewButton.setFont(new Font("HP Simplified", Font.BOLD, 31));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 239, 91));
		btnNewButton.addActionListener(new ActionListener() {
			// Login button
			public void actionPerformed(ActionEvent e) {
				///////////////////////////////////


				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://:/", "***", "***");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT numeroidentificacion FROM usuarios");
					ArrayList<String> listLogin = new ArrayList<>();
					while (rs.next()) {
						String valor = rs.getString("numeroidentificacion");
						listLogin.add(valor);
					}
					String[] arrayLogin = listLogin.toArray(new String[0]);
					rs.close();
					stmt.close();

					Statement stmtRol = conn.createStatement();
					ResultSet rsRol = stmtRol.executeQuery("SELECT rol FROM usuarios");
					ArrayList<String> listRol = new ArrayList<>();
					while (rsRol.next()) {
						String valor = rsRol.getString("rol");
						listRol.add(valor);
					}
					String[] arrayRol = listRol.toArray(new String[0]);
					rsRol.close();
					stmtRol.close();
					
					
					Statement stmtCurrentUser = conn.createStatement();
					ResultSet rsCurrentUser = stmtCurrentUser.executeQuery("SELECT numeroidentificacion FROM usuariosActuales");
					ArrayList<String> listCurrentUsers = new ArrayList<>();
					while (rsCurrentUser.next()) {
						String valor = rs.getString("numeroidentificacion");
						listCurrentUsers.add(valor);
					}
					String[] arrayCurrentUsers = listCurrentUsers.toArray(new String[0]);
					rsCurrentUser.close();
					stmtCurrentUser.close();
					
					
					
					int counter = 0;
					int counterCurrentUser = 0;


					for(int ii = 0; ii < arrayLogin.length; ii++) {
						if(textField.getText().equals(arrayLogin[ii])) {
							counter = counter + 1;
							positionNumber = ii;
							IdentificationNumber = arrayLogin[ii];
						}//if

					}// for
					
					
					for(int jj = 0; jj < arrayCurrentUsers.length; jj++) {
						if(textField.getText().equals(arrayCurrentUsers[jj])) {
							counterCurrentUser = counterCurrentUser + 1;				
						}// if
					}// for
					
					
					if(counterCurrentUser > 0) {
						
						
						
						conn.close();
					}// if(counterCurrentUser > 0)
					
					
					else {
					
					

					if(counter > 0) {


						if(arrayRol[positionNumber].equals("Vigilante")) {

							LoginSecurity l = new LoginSecurity();
							l.setVisible(true);
							dispose(); //Close the current window


						}// Security.


						else {

							SelectCar p = new SelectCar();
							p.setVisible(true);
							dispose(); //Close the current window


						}// Students, Teachers and others.

					}// if(counter > 0)

					else {
						JOptionPane.showMessageDialog(null, "El usuario no ha sido encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
						textField.setText("");
					}// else
					
					conn.close();
				}// else - Current User

					

				}
				catch(SQLException i){
					i.printStackTrace();
				}// catch


				///////////////////////////////////
			}


		});
		btnNewButton.setBounds(476, 483, 320, 57);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(10, 10, 343, 132);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon("Media\\logo-upb-blanco1.png"));


	}
}
