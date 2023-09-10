package co.edu.upb.upark;

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
import java.awt.event.ActionEvent;

public class SelectCar extends JFrame {

	private JPanel contentPane;

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
		
		
		
		int numberPosition = Login.positionNumber;
		String numberIdentification = Login.IdentificationNumber;
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 649, 390);
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
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("Media\\logo-upb-blanco1.png"));
		
		JLabel lblNewLabel_1 = new JLabel("Escoge tu vehículo");
		lblNewLabel_1.setFont(new Font("HP Simplified", Font.BOLD, 99));
		lblNewLabel_1.setBounds(238, 124, 795, 124);
		contentPane.add(lblNewLabel_1);
		
		String name = "";
		String[] vehicles = new String[2];
	

		
		// --------------------- Get the name of the vehicle owner  and the array of vehicles ---------------------
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://35.222.147.13:3306/parqueadero", "root", "842963");
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
			
			Statement stmt3 = conn.createStatement();
			
			String query = "SELECT * FROM vehiculos WHERE numeroidentificacion = '" + numberIdentification + "'"; 
			ResultSet rs3 = stmt3.executeQuery(query);
			String rowString1 = "";
			String rowString2 = "";
			int count = 0;
			while (rs3.next()) {
			    count++;
			    if (count == 1) {
			        for (int jj = 2; jj <= rs3.getMetaData().getColumnCount(); jj++) {
			            rowString1 += rs3.getString(jj) + "  ";
			        }
			    } else if (count == 2) {
			        for (int jj = 2; jj <= rs3.getMetaData().getColumnCount(); jj++) {
			            rowString2 += rs3.getString(jj) + "  ";
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

		
		// ------------------------------------------------------------------------------
		
		
		
		
		JLabel lblNewLabel_2 = new JLabel(name);
		lblNewLabel_2.setFont(new Font("HP Simplified", Font.BOLD, 36));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(192, 258, 888, 49);
		contentPane.add(lblNewLabel_2);
		
		JComboBox comboBox = new JComboBox();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement(vehicles[0]);
		model.addElement(vehicles[1]);
		comboBox.setModel(model);
		comboBox.setFont(new Font("HP Simplified", Font.BOLD, 29));
		comboBox.setBounds(216, 345, 840, 71);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("INGRESAR");
		btnNewButton.setFont(new Font("HP Simplified", Font.BOLD, 31));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 239, 91));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(416, 468, 440, 76);
		contentPane.add(btnNewButton);
		
		
		
		
	}
}
