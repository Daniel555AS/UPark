package co.edu.upb.upark;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

public class SelectTypeOfLicensePlate extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static int typeOfLicensePlate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectTypeOfLicensePlate frame = new SelectTypeOfLicensePlate();
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
	public SelectTypeOfLicensePlate() {
		AddVisitorVehicle addVisitorVehicle = new AddVisitorVehicle();
		this.setResizable(false); // Disable the maximize window option
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the operation that will be performed when the window is closed
		setBounds(100, 100, 1286, 660); // Size and position of the frame

		// Creating a new JPanel and setting its properties:
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane); // Set the content pane of the frame to the new JPanel
		contentPane.setLayout(null); // Set the layout of the panel to null

		// Creation of the black JPanel, located at the bottom of the window:
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 594, 1287, 29);
		contentPane.add(panel_1);

		// Creation of a JLabel with the Logo of the University:
		JLabel lblUpbLogo = new JLabel("");
		lblUpbLogo.setBounds(10, 10, 343, 132);
		contentPane.add(lblUpbLogo);
		lblUpbLogo.setIcon(new ImageIcon("Media\\logo-upb-blanco1.png"));

		// Creation of a JLabel containing the text: "Tipo de Placa":
		JLabel lblTipoPlaca = new JLabel("Tipo de Placa");
		lblTipoPlaca.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 97));
		lblTipoPlaca.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoPlaca.setBounds(387, 52, 774, 90);
		contentPane.add(lblTipoPlaca);

		ImageButton diplomaticVehicle = new ImageButton("", "Media\\DiplomaticVehicle.jpg");
		diplomaticVehicle.setBounds(86, 371, 400, 116);
		contentPane.add(diplomaticVehicle);
		diplomaticVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				typeOfLicensePlate = 3;
				addVisitorVehicle.setVisible(true);
				dispose(); // Close the current window
			}
		});

		ImageButton privateVehicle = new ImageButton("", "Media\\PrivateVehicle.jpg");
		privateVehicle.setBounds(86, 171, 400, 116);
		contentPane.add(privateVehicle);
		privateVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				typeOfLicensePlate = 1;
				addVisitorVehicle.setVisible(true);
				dispose(); // Close the current window
			}
		});

		ImageButton classicVehicle = new ImageButton("", "Media\\ClassicVehicle.jpg");
		classicVehicle.setBounds(785, 371, 400, 116);
		contentPane.add(classicVehicle);
		classicVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				typeOfLicensePlate = 4;
				addVisitorVehicle.setVisible(true);
				dispose(); // Close the current window
			}
		});

		ImageButton publicVehicle = new ImageButton("", "Media\\PublicVehicle.jpg");
		publicVehicle.setBounds(786, 171, 400, 116);
		contentPane.add(publicVehicle);
		publicVehicle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				typeOfLicensePlate = 1;
				addVisitorVehicle.setVisible(true);
				dispose(); // Close the current window
			}
		});

		JLabel lblNewLabel = new JLabel("Vehículo Particular");
		lblNewLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(86, 291, 399, 39);
		contentPane.add(lblNewLabel);

		JLabel lblVehculoPblico = new JLabel("Vehículo Público");
		lblVehculoPblico.setHorizontalAlignment(SwingConstants.CENTER);
		lblVehculoPblico.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 30));
		lblVehculoPblico.setBounds(786, 291, 399, 39);
		contentPane.add(lblVehculoPblico);

		JLabel lblVehculoAntiguoYo = new JLabel("Vehículo Antiguo y Clásico");
		lblVehculoAntiguoYo.setHorizontalAlignment(SwingConstants.CENTER);
		lblVehculoAntiguoYo.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 30));
		lblVehculoAntiguoYo.setBounds(786, 492, 399, 39);
		contentPane.add(lblVehculoAntiguoYo);

		JLabel lblVehculoDiplomticaConsular = new JLabel("Vehículo Diplomática, Consular y");
		lblVehculoDiplomticaConsular.setHorizontalAlignment(SwingConstants.CENTER);
		lblVehculoDiplomticaConsular.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 30));
		lblVehculoDiplomticaConsular.setBounds(48, 492, 475, 39);
		contentPane.add(lblVehculoDiplomticaConsular);

		JLabel lblDeMisionesEspeciales = new JLabel("de Misiones Especiales");
		lblDeMisionesEspeciales.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeMisionesEspeciales.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 30));
		lblDeMisionesEspeciales.setBounds(86, 526, 399, 39);
		contentPane.add(lblDeMisionesEspeciales);
	} // public SelectTypeOfLicensePlate()
} // public class SelectTypeOfLicensePlate extends JFrame