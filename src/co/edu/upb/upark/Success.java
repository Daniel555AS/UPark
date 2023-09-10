package co.edu.upb.upark;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Success extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Success frame = new Success();
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
	public Success() {
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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 10, 343, 132);
		lblNewLabel.setIcon(new ImageIcon("Media\\logo-upb-blanco1.png"));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("INGRESO EXITOSO");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("HP Simplified", Font.BOLD, 99));
		lblNewLabel_1.setBounds(188, 153, 895, 124);
		contentPane.add(lblNewLabel_1);
		

		
		String name = "";
		int numberPosition = Login.positionNumber;
		
		// ------------------ Get the name of the vehicle owner ------------------
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://:/", "***", "***");
			Statement stmtSuccess = conn.createStatement();
			ResultSet rsSuccess = stmtSuccess.executeQuery("SELECT nombre FROM usuarios");
			ArrayList<String> listSuccess = new ArrayList<>();
			while (rsSuccess.next()) {
				String valor = rsSuccess.getString("nombre");
				listSuccess.add(valor);
			}
			String[] arraySuccess= listSuccess.toArray(new String[0]);
			rsSuccess.close();
			stmtSuccess.close();

			name = arraySuccess[numberPosition];	
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		// -----------------------------------------------------------------------
		
		JLabel lblNewLabel_2 = new JLabel(name);
		lblNewLabel_2.setFont(new Font("HP Simplified", Font.BOLD, 36));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(195, 301, 888, 49);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("¡Gracias Por Usar Nuestro Servicio!");
		lblNewLabel_3.setForeground(new Color(172, 50, 9));
		lblNewLabel_3.setFont(new Font("HP Simplified", Font.BOLD, 70));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(97, 410, 1078, 82);
		contentPane.add(lblNewLabel_3);
		
	}// public Success()

}
