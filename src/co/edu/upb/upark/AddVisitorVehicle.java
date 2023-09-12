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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddVisitorVehicle extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddVisitorVehicle frame = new AddVisitorVehicle();
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
	public AddVisitorVehicle() {
		
		this.setResizable(false); // Disable the maximize window option
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1286, 660);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Cambria", Font.BOLD, 25));
		textField_2.setBounds(51, 390, 544, 49);
		textField_2.setBackground(new Color(237, 238, 223));
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Cambria", Font.BOLD, 25));
		textField_1.setBounds(671, 391, 544, 48);
		textField_1.setBackground(new Color(237, 238, 223));
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 594, 1287, 29);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 10, 343, 132);
		lblNewLabel.setIcon(new ImageIcon("Media\\logo-upb-blanco1.png"));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Datos del Vehículo");
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD, 85));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(404, 42, 748, 90);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Cambria", Font.BOLD, 25));
		textField.setBounds(51, 232, 544, 49);
		textField.setBackground(new Color(237, 238, 223));
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("REGISTRAR");
		btnNewButton.setBackground(new Color(255, 239, 91));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://35.222.147.13:3306/parqueadero", "root", "842963");
					
					String query = "INSERT INTO vehiculos (marca, placa, color, modelo, numeroidentificacion) VALUES (?, ?, ?, ?, ?)";
					PreparedStatement preparedStatement = conn.prepareStatement(query);
					
					String visitorDoc = AddVisitor.visitorDocument;
					System.out.println(visitorDoc);
							
		            preparedStatement.setString(1, textField_3.getText());
		            preparedStatement.setString(2, textField.getText());
		            preparedStatement.setString(3, textField_2.getText());
		            preparedStatement.setString(4, textField_1.getText());
		            preparedStatement.setString(5, visitorDoc);
		            preparedStatement.executeUpdate();
		            
		            dispose();
		            JOptionPane.showMessageDialog(null, "Datos del Vehículo Guardados con Éxito", "VEHÍCULO", JOptionPane.INFORMATION_MESSAGE);
		            JOptionPane.showMessageDialog(null, "El Visitante Ha Sido Registrado Con Éxito", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
					
					conn.close();
				}

				catch(SQLException i){
					i.printStackTrace();
				}// catch
								
			}// public void actionPerformed(ActionEvent e)
		});
		btnNewButton.setFont(new Font("Cambria", Font.BOLD, 44));
		btnNewButton.setBounds(455, 511, 366, 73);
		contentPane.add(btnNewButton);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Cambria", Font.BOLD, 25));
		textField_3.setBounds(671, 232, 544, 49);
		textField_3.setBackground(new Color(237, 238, 223));
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Placa:");
		lblNewLabel_2.setFont(new Font("Cambria", Font.BOLD, 32));
		lblNewLabel_2.setBounds(51, 193, 192, 29);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Marca:");
		lblNewLabel_3.setFont(new Font("Cambria", Font.BOLD, 32));
		lblNewLabel_3.setBounds(671, 193, 122, 29);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Color:");
		lblNewLabel_4.setFont(new Font("Cambria", Font.BOLD, 32));
		lblNewLabel_4.setBounds(51, 351, 122, 29);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Modelo:");
		lblNewLabel_5.setFont(new Font("Cambria", Font.BOLD, 32));
		lblNewLabel_5.setBounds(671, 351, 166, 30);
		contentPane.add(lblNewLabel_5);
	}
}
