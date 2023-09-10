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

	private JPanel contentPane;
	private JPasswordField passwordField;

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

		JLabel lblNewLabel_1 = new JLabel("Interfaz Seguridad");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(207, 143, 857, 124);
		lblNewLabel_1.setFont(new Font("HP Simplified", Font.BOLD, 99));
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Contraseña");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(535, 299, 201, 39);
		lblNewLabel_2.setFont(new Font("HP Simplified", Font.BOLD, 30));
		contentPane.add(lblNewLabel_2);

		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(237, 238, 223));
		passwordField.setBounds(440, 348, 392, 67);
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 25));
		contentPane.add(passwordField);

		JButton btnNewButton = new JButton("REGRESAR");
		btnNewButton.setFont(new Font("HP Simplified", Font.BOLD, 31));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(243, 37, 68));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Login ls = new Login();
				ls.setVisible(true);
				dispose(); //Close the current window

			}
		});
		btnNewButton.setBounds(10, 527, 187, 57);
		contentPane.add(btnNewButton);


		JButton btnNewButton_1 = new JButton("ACCEDER");
		btnNewButton_1.setFont(new Font("HP Simplified", Font.BOLD, 31));
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setBackground(new Color(255, 239, 91));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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



					if(arrayLoginSecurity[positionLoginSecurity].equals(passwordFinal)) {

						MenuSecurity m = new MenuSecurity();
						m.setVisible(true);
						dispose(); //Close the current window



					}// if

					else {
						JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "ERROR", JOptionPane.ERROR_MESSAGE);
						passwordField.setText("");
					}// else

					conn.close();

				}
				catch(SQLException i){
					i.printStackTrace();
				}// catch





			}
		});
		btnNewButton_1.setBounds(476, 446, 320, 57);
		contentPane.add(btnNewButton_1);






	}

}
