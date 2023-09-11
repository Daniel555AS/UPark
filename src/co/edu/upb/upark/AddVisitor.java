package co.edu.upb.upark;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AddVisitor extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddVisitor frame = new AddVisitor();
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
	public AddVisitor() {
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
		lblNewLabel.setIcon(new ImageIcon("Media\\logo-upb-blanco1.png"));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Datos del Visitante");
		lblNewLabel_1.setFont(new Font("HP Simplified", Font.BOLD, 88));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(404, 42, 748, 90);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("HP Simplified", Font.BOLD, 30));
		comboBox.addItem("Hombre");
		comboBox.addItem("Mujer");
		comboBox.setToolTipText("\r\nHombre\r\nMujer");
		comboBox.setBounds(49, 406, 506, 54);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("SIGUIENTE");
		btnNewButton.setBackground(new Color(255, 239, 91));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://:/", "***", "***");
					
					String query = "INSERT INTO usuarios (numeroidentificacion, nombre, genero, rol, documento) VALUES (?, ?, ?, ?, ?)";
					PreparedStatement preparedStatement = conn.prepareStatement(query);
					
		            preparedStatement.setString(1, textField.getText());
		            preparedStatement.setString(2, textField_1.getText());
		            preparedStatement.setString(3, (String) comboBox.getSelectedItem());
		            preparedStatement.setString(4, "Visitante");
		            preparedStatement.setString(5, textField.getText());
		            preparedStatement.executeUpdate();
					
					conn.close();
				}

				catch(SQLException i){
					i.printStackTrace();
				}// catch
			}
		});
		btnNewButton.setFont(new Font("HP Simplified", Font.BOLD, 42));
		btnNewButton.setBounds(455, 511, 366, 73);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setFont(new Font("HP Simplified", Font.BOLD, 28));
		textField.setBackground(new Color(237, 238, 223));
		textField.setBounds(49, 313, 1129, 54);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("HP Simplified", Font.BOLD, 28));
		textField_1.setBackground(new Color(237, 238, 223));
		textField_1.setBounds(49, 208, 1129, 54);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre Completo:");
		lblNewLabel_2.setFont(new Font("HP Simplified", Font.BOLD, 26));
		lblNewLabel_2.setBounds(49, 169, 279, 29);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("No. Documento");
		lblNewLabel_3.setFont(new Font("HP Simplified", Font.BOLD, 26));
		lblNewLabel_3.setBounds(49, 272, 321, 31);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Género:");
		lblNewLabel_4.setFont(new Font("HP Simplified", Font.BOLD, 26));
		lblNewLabel_4.setBounds(49, 377, 166, 19);
		contentPane.add(lblNewLabel_4);
		

        
		
		
		
		
		
	}
}

