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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RoundedTextField documentoTextField;
	private RoundedTextField nombreTextField;
	public static String visitorDocument;
	public static String visitorName;

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

		this.setResizable(false); // Disable the maximize window option

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
		
		// // Creation of a JLabel containing the text: "Datos del Visitante":
		JLabel lblNewLabel_1 = new JLabel("Datos del Visitante");
		lblNewLabel_1.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 80));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(404, 42, 774, 90);
		contentPane.add(lblNewLabel_1);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 30));
		comboBox.addItem("Hombre");
		comboBox.addItem("Mujer");
		comboBox.setToolTipText("\r\nHombre\r\nMujer");
		comboBox.setBounds(49, 416, 506, 54);
		contentPane.add(comboBox);
		
		// Creation of the Button: "SIGUIENTE":
		RoundedButton siguienteButton = new RoundedButton("SIGUIENTE", new Color(255, 239, 91), new Color(247, 208, 57), 1000);
		siguienteButton.setBackground(new Color(255, 239, 91));
		siguienteButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 31));
		siguienteButton.setBounds(476, 511, 320, 57);
		contentPane.add(siguienteButton);
		siguienteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String text1 = documentoTextField.getText().trim();
				String text2 = nombreTextField.getText().trim();

				if(text1.equals("") || text1.length() == 0 || text2.equals("") || text2.length() == 0) {


					JOptionPane.showMessageDialog(null, "Debe llenar todos los campos.", "ERROR - Campo(s) vacío(s)", JOptionPane.ERROR_MESSAGE);
					documentoTextField.setText("");
					nombreTextField.setText("");
				}

				else {

					try {
						Connection conn = DriverManager.getConnection("jdbc:mysql://:/", "***", "***");

						String query = "INSERT INTO usuarios (numeroidentificacion, nombre, genero, rol, documento) VALUES (?, ?, ?, ?, ?)";
						PreparedStatement preparedStatement = conn.prepareStatement(query);

						preparedStatement.setString(1, documentoTextField.getText());
						preparedStatement.setString(2, nombreTextField.getText());
						preparedStatement.setString(3, (String) comboBox.getSelectedItem());
						preparedStatement.setString(4, "Visitante");
						preparedStatement.setString(5, documentoTextField.getText());
						preparedStatement.executeUpdate();

						visitorDocument = documentoTextField.getText();
						visitorName = nombreTextField.getText();

						dispose();
						JOptionPane.showMessageDialog(null, "Datos del Visitante Guardados con Éxito", "VISITANTE", JOptionPane.INFORMATION_MESSAGE);

						AddVisitorVehicle a = new AddVisitorVehicle();
						a.setVisible(true);

						conn.close();

					}//try

					catch(SQLException i){
						i.printStackTrace();
					}//catch
				}//else

			}//public void actionPerformed(ActionEvent e) 
		});
		
		// Creation of the Button: "REGRESAR":
		RoundedButton regresarButton = new RoundedButton("REGRESAR", new Color(229, 86, 109), new Color(216, 58, 58), 1000);
		regresarButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 31));
		regresarButton.setForeground(new Color(0, 0, 0));
		regresarButton.setBackground(new Color(243, 37, 68));
		regresarButton.setBounds(1021, 511, 221, 57);
		contentPane.add(regresarButton);
		regresarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MenuSecurity menuSecurityAddVisitor = new MenuSecurity();
				menuSecurityAddVisitor.setVisible(true);
				dispose(); //Close the current window

			}
		});

		documentoTextField = new RoundedTextField(50);
		documentoTextField.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 31));
		documentoTextField.setBackground(new Color(237, 238, 223));
		documentoTextField.setBounds(49, 300, 1129, 54);
		contentPane.add(documentoTextField);
		documentoTextField.setColumns(10);

		nombreTextField = new RoundedTextField(50);
		nombreTextField.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 30));
		nombreTextField.setBackground(new Color(237, 238, 223));
		nombreTextField.setBounds(49, 187, 1129, 54);
		contentPane.add(nombreTextField);
		nombreTextField.setColumns(10);
		
		// Creation of a JLabel containing the text: "Nombre Completo:":
		JLabel nombreLabel = new JLabel("Nombre Completo:");
		nombreLabel.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 35));
		nombreLabel.setBounds(49, 139, 279, 46);
		contentPane.add(nombreLabel);
		
		// Creation of a JLabel containing the text: "No. Documento:":
		JLabel documentoLabel = new JLabel("No. Documento:");
		documentoLabel.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 35));
		documentoLabel.setBounds(49, 265, 321, 31);
		contentPane.add(documentoLabel);
		
		// Creation of a JLabel containing the text: "Género:":
		JLabel generoLabel = new JLabel("Género:");
		generoLabel.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 35));
		generoLabel.setBounds(49, 377, 166, 29);
		contentPane.add(generoLabel);	

	} // public AddVisitor()

} // public class AddVisitor extends JFrame

