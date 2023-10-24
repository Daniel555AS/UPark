package co.edu.upb.upark;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.text.*;

public class AddVisitorVehicle extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RoundedTextField placaTextField;
	private RoundedTextField modeloTextField;
	private RoundedTextField colorTextField;
	private RoundedTextField marcaTextField;
	private JLabel placaLabel;
	private JLabel marcaLabel;
	private JLabel colorLabel;
	private JLabel modeloLabel;
	private String text1 = "";
	private DatabaseManager databaseManager = new DatabaseManager();
	private SoundPlayer soundPlayer = new SoundPlayer();

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

		colorTextField = new RoundedTextField(50);
		colorTextField.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 30));
		colorTextField.setBounds(51, 390, 544, 60);
		colorTextField.setBackground(new Color(237, 238, 223));
		contentPane.add(colorTextField);
		colorTextField.setColumns(10);

		modeloTextField = new RoundedTextField(50);
		modeloTextField.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 30));
		modeloTextField.setBounds(671, 390, 544, 60);
		modeloTextField.setBackground(new Color(237, 238, 223));
		contentPane.add(modeloTextField);
		modeloTextField.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 594, 1287, 29);
		contentPane.add(panel);

		// Creation of a JLabel with the Logo of the University:
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 10, 343, 132);
		lblNewLabel.setIcon(new ImageIcon("Media\\logo-upb-blanco1.png"));
		contentPane.add(lblNewLabel);

		// Creation of a JLabel containing the text: "Datos del Vehículo":
		JLabel lblNewLabel_1 = new JLabel("Datos del Vehículo");
		lblNewLabel_1.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 85));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(404, 42, 811, 90);
		contentPane.add(lblNewLabel_1);

		placaTextField = new RoundedTextField(50);
		placaTextField.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 30));
		placaTextField.setBounds(51, 232, 544, 60);
		placaTextField.setBackground(new Color(237, 238, 223));
		contentPane.add(placaTextField);
		placaTextField.setColumns(10);

		((AbstractDocument) placaTextField.getDocument()).setDocumentFilter(new DocumentFilter() {
			@Override
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
					throws BadLocationException {
				fb.insertString(offset, string.toUpperCase(), attr);
			}

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				fb.replace(offset, length, text.toUpperCase(), attrs);
			}
		});

		// Creation of the Button: "REGISTRAR":
		RoundedButton registrarButton = new RoundedButton("REGISTRAR", new Color(255, 239, 91), new Color(247, 208, 57),
				1000);
		registrarButton.setBackground(new Color(255, 239, 91));
		registrarButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 31));
		registrarButton.setBounds(476, 495, 320, 57);
		registrarButton.setFocusable(false);
		contentPane.add(registrarButton);
		registrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				text1 = placaTextField.getText().trim();
				String text2 = modeloTextField.getText().trim();
				String text3 = colorTextField.getText().trim();
				String text4 = marcaTextField.getText().trim();
				if (text1.equals("") || text1.length() == 0 || text2.equals("") || text2.length() == 0
						|| text3.equals("") || text3.length() == 0 || text4.equals("") || text4.length() == 0) {
					soundPlayer.playSound("Media\\ErrorSound.wav");
					JOptionPane.showMessageDialog(null, "Debe llenar todos los campos.", "ERROR - Campo(s) vacío(s)",
							JOptionPane.ERROR_MESSAGE);

					placaTextField.setText("");
					modeloTextField.setText("");
					colorTextField.setText("");
					marcaTextField.setText("");

				} else {
					boolean verificationPlate = false;
					if ((SelectTypeOfLicensePlate.typeOfLicensePlate == 1) && (text1.length() == 6)
							&& (text1.charAt(0) >= 'A' && text1.charAt(0) <= 'Z')
							&& (text1.charAt(1) >= 'A' && text1.charAt(1) <= 'Z')
							&& (text1.charAt(2) >= 'A' && text1.charAt(2) <= 'Z')
							&& (Character.isDigit(text1.charAt(3)) && text1.charAt(3) >= '0' && text1.charAt(3) <= '9')
							&& (Character.isDigit(text1.charAt(4)) && text1.charAt(4) >= '0' && text1.charAt(4) <= '9')
							&& (Character.isDigit(text1.charAt(5)) && text1.charAt(5) >= '0'
									&& text1.charAt(5) <= '9')) {
						verificationPlate = true;
					} // PrivateVehicle

					if ((SelectTypeOfLicensePlate.typeOfLicensePlate == 2) && (text1.length() == 6)
							&& (text1.charAt(0) >= 'A' && text1.charAt(0) <= 'Z')
							&& (text1.charAt(1) >= 'A' && text1.charAt(1) <= 'Z')
							&& (text1.charAt(2) >= 'A' && text1.charAt(2) <= 'Z')
							&& (Character.isDigit(text1.charAt(3)) && text1.charAt(3) >= '0' && text1.charAt(3) <= '9')
							&& (Character.isDigit(text1.charAt(4)) && text1.charAt(4) >= '0' && text1.charAt(4) <= '9')
							&& (Character.isDigit(text1.charAt(5)) && text1.charAt(5) >= '0'
									&& text1.charAt(5) <= '9')) {
						verificationPlate = true;
					} // PrivateVehicle// PublicVehicle

					if (SelectTypeOfLicensePlate.typeOfLicensePlate == 3 && (text1.length() == 6)
							&& (text1.charAt(0) == 'A' || text1.charAt(0) == 'C' || text1.charAt(0) == 'D'
									|| text1.charAt(0) == 'O' || text1.charAt(0) == 'M')
							&& (text1.charAt(1) == 'A' || text1.charAt(1) == 'C' || text1.charAt(1) == 'D'
									|| text1.charAt(1) == 'O' || text1.charAt(1) == 'M')
							&& (Character.isDigit(text1.charAt(2)) && text1.charAt(2) >= '0' && text1.charAt(2) <= '9')
							&& (Character.isDigit(text1.charAt(3)) && text1.charAt(3) >= '0' && text1.charAt(3) <= '9')
							&& (Character.isDigit(text1.charAt(4)) && text1.charAt(4) >= '0' && text1.charAt(4) <= '9')
							&& (Character.isDigit(text1.charAt(5)) && text1.charAt(5) >= '0'
									&& text1.charAt(5) <= '9')) {
						verificationPlate = true;
					} // DiplomaticVehicle

					if ((SelectTypeOfLicensePlate.typeOfLicensePlate == 4) && (text1.length() == 6)
							&& (text1.charAt(0) >= 'A' && text1.charAt(0) <= 'Z')
							&& (text1.charAt(1) >= 'A' && text1.charAt(1) <= 'Z')
							&& (text1.charAt(2) >= 'A' && text1.charAt(2) <= 'Z')
							&& (Character.isDigit(text1.charAt(3)) && text1.charAt(3) >= '0' && text1.charAt(3) <= '9')
							&& (Character.isDigit(text1.charAt(4)) && text1.charAt(4) >= '0' && text1.charAt(4) <= '9')
							&& (Character.isDigit(text1.charAt(5)) && text1.charAt(5) >= '0'
									&& text1.charAt(5) <= '9')) {
						verificationPlate = true;
					} // ClassicVehicle

					if (verificationPlate == true) {
						String[] platesFromUsuariosActuales = databaseManager.platesFromUsuariosActuales();
						int countPlatesFromUsuariosActuales = 0;

						for (int ii = 0; ii < platesFromUsuariosActuales.length; ii++) {
							if (text1.equals(platesFromUsuariosActuales[ii]) == true) {
								countPlatesFromUsuariosActuales++;
							}
						}

						System.out.println(platesFromUsuariosActuales[0]);
						System.out.println(text1);
						System.out.println(countPlatesFromUsuariosActuales);

						if (countPlatesFromUsuariosActuales > 0) {
							JOptionPane.showMessageDialog(null,
									"El Vehículo Asociado a la Placa Ingresada, Ya Se Encuentra Dentro del Parqueadero.",
									"ERROR - PLACA NO VÁLIDA", JOptionPane.ERROR_MESSAGE);
							placaTextField.setText("");
						}

						else {
							try {
								Connection conn = DatabaseConnection.getConnection();
								String query = "INSERT INTO vehiculos (marca, placa, color, modelo, numeroidentificacion) VALUES (?, ?, ?, ?, ?)";
								PreparedStatement preparedStatement = conn.prepareStatement(query);
								String visitorDoc = AddVisitor.visitorDocument;

								preparedStatement.setString(1, marcaTextField.getText());
								preparedStatement.setString(2, text1);
								preparedStatement.setString(3, colorTextField.getText());
								preparedStatement.setString(4, modeloTextField.getText());
								preparedStatement.setString(5, visitorDoc);
								preparedStatement.executeUpdate();

								String queryVisitor = "INSERT INTO usuariosActuales (numeroidentificacion, nombreusuario, documentousuario, placa) VALUES (?, ?, ?, ?)";
								PreparedStatement preparedStatementVisitor = conn.prepareStatement(queryVisitor);
								String visitorNamee = AddVisitor.visitorName;

								preparedStatementVisitor.setString(1, visitorDoc);
								preparedStatementVisitor.setString(2, visitorNamee);
								preparedStatementVisitor.setString(3, visitorDoc);
								preparedStatementVisitor.setString(4, placaTextField.getText());
								preparedStatementVisitor.executeUpdate();

								dispose();
								soundPlayer.playSound("Media\\AccessSound.wav");
								JOptionPane.showMessageDialog(null, "Datos del Vehículo Guardados con Éxito",
										"VEHÍCULO", JOptionPane.INFORMATION_MESSAGE);
								JOptionPane.showMessageDialog(null, "El Visitante Ha Sido Registrado Con Éxito",
										"ÉXITO", JOptionPane.INFORMATION_MESSAGE);
								MenuSecurity menuSecurityAddVisitorVehicle = new MenuSecurity();
								menuSecurityAddVisitorVehicle.setVisible(true);
								conn.close();
							} catch (SQLException i) {
								i.printStackTrace();
							} 
						}
					} 
					else {
						if (SelectTypeOfLicensePlate.typeOfLicensePlate == 1) {
							soundPlayer.playSound("Media\\ErrorSound.wav");
							JOptionPane.showMessageDialog(null,
									"La Placa Ingresada No Corresponde a la Forma: 'AAA123' Sin Espacios Entre Caracteres.",
									"ERROR - PLACA NO VÁLIDA", JOptionPane.ERROR_MESSAGE);
							placaTextField.setText("");
						} else if (SelectTypeOfLicensePlate.typeOfLicensePlate == 2) {
							soundPlayer.playSound("Media\\ErrorSound.wav");
							JOptionPane.showMessageDialog(null,
									"La Placa Ingresada No Corresponde a la Forma: 'AAA123' Sin Espacios Entre Caracteres.",
									"ERROR - PLACA NO VÁLIDA", JOptionPane.ERROR_MESSAGE);
							placaTextField.setText("");
						} else if (SelectTypeOfLicensePlate.typeOfLicensePlate == 3) {
							soundPlayer.playSound("Media\\ErrorSound.wav");
							JOptionPane.showMessageDialog(null,
									"La Placa Ingresada No Corresponde a la Forma: 'AA1234' Sin Espacios Entre Caracteres.",
									"ERROR - PLACA NO VÁLIDA", JOptionPane.ERROR_MESSAGE);
							placaTextField.setText("");
						} else {
							soundPlayer.playSound("Media\\ErrorSound.wav");
							JOptionPane.showMessageDialog(null,
									"La Placa Ingresada No Corresponde a la Forma: 'AAA123' Sin Espacios Entre Caracteres.",
									"ERROR - PLACA NO VÁLIDA", JOptionPane.ERROR_MESSAGE);
							placaTextField.setText("");
						}
					} 
				}
			}

		});

		marcaTextField = new RoundedTextField(50);
		marcaTextField.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 30));
		marcaTextField.setBounds(671, 232, 544, 60);
		marcaTextField.setBackground(new Color(237, 238, 223));
		contentPane.add(marcaTextField);
		marcaTextField.setColumns(10);

		// Creation of a JLabel containing the text: "Placa:":
		placaLabel = new JLabel("Placa:");
		placaLabel.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 35));
		placaLabel.setBounds(51, 193, 192, 29);
		contentPane.add(placaLabel);

		// Creation of a JLabel containing the text: "Marca:":
		marcaLabel = new JLabel("Marca:");
		marcaLabel.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 35));
		marcaLabel.setBounds(671, 193, 122, 29);
		contentPane.add(marcaLabel);

		// Creation of a JLabel containing the text: "Color:":
		colorLabel = new JLabel("Color:");
		colorLabel.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 35));
		colorLabel.setBounds(51, 339, 122, 41);
		contentPane.add(colorLabel);

		// Creation of a JLabel containing the text: "Modelo:":
		modeloLabel = new JLabel("Modelo:");
		modeloLabel.setFont(new Font("Franklin Gothic Demi Cond", Font.BOLD, 35));
		modeloLabel.setBounds(671, 351, 166, 30);
		contentPane.add(modeloLabel);

	} // public AddVisitorVehicle()

} // public class AddVisitorVehicle extends JFrame

