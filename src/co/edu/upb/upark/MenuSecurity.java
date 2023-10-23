package co.edu.upb.upark;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MenuSecurity extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String nameMenuSecurity = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuSecurity frame = new MenuSecurity();
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
	public MenuSecurity() {
		
		this.setResizable(false); // Disable the maximize window option
		
		int numberPosition = Login.positionNumber;
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://:/", "***", "***");
			Statement stmtMenuSecurity = conn.createStatement();
			ResultSet rsMenuSecurity = stmtMenuSecurity.executeQuery("SELECT nombre FROM usuarios");
			ArrayList<String> listMenuSecurity = new ArrayList<>();
			while (rsMenuSecurity.next()) {
				String valor = rsMenuSecurity.getString("nombre");
				listMenuSecurity.add(valor);
			}
			String[] arrayMenuSecurity = listMenuSecurity.toArray(new String[0]);
			rsMenuSecurity.close();
			stmtMenuSecurity.close();

			nameMenuSecurity = arrayMenuSecurity[numberPosition];	
			
			conn.close();
		}
		catch(SQLException i){
			i.printStackTrace();
		}
		
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
		
		// Creation of the Button: "Usuarios en Parqueadero":
		RoundedButton usuariosParqueaderoButton = new RoundedButton("Usuarios en Parqueadero", new Color(255, 255, 255),new Color(196, 193, 186), 1000);
		usuariosParqueaderoButton.setBackground(new Color(255, 255, 255));
		usuariosParqueaderoButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 31));
		usuariosParqueaderoButton.setBounds(785, 352, 404, 77);
		contentPane.add(usuariosParqueaderoButton);
		usuariosParqueaderoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		usuariosParqueaderoButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Crear una instancia de UsuariosEnParqueadero y mostrar la ventana
		        UsuariosEnParqueadero ventanaUsuarios = new UsuariosEnParqueadero();
		        ventanaUsuarios.mostrarDatosUsuarios();
		        ventanaUsuarios.setVisible(true);
		    }
		});
		
		// Creation of the Button: "Historial":
		RoundedButton historialButton = new RoundedButton("Historial", new Color(255, 255, 255),new Color(196, 193, 186), 1000);
		historialButton.setBackground(new Color(255, 255, 255));
		historialButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 31));
		historialButton.setBounds(365, 351, 389, 77);
		contentPane.add(historialButton);
		historialButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        HistorialVentana ventanaHistorial = new HistorialVentana();
		        ventanaHistorial.mostrarDatosHistorial();
		        ventanaHistorial.setVisible(true);
		    }
		});
		
		// Creation of the Button: "Cerrar Sesión":
		RoundedButton cerrarSesionButton = new RoundedButton("Cerrar Sesión", new Color(229, 86, 109), new Color(216, 58, 58), 1000);
		cerrarSesionButton.setBackground(new Color(243, 37, 68));
		cerrarSesionButton.setForeground(new Color(0, 0, 0));
		cerrarSesionButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 31));
		cerrarSesionButton.setBounds(858, 489, 389, 77);
		contentPane.add(cerrarSesionButton);
		cerrarSesionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login loginMenuSecurity = new Login();
				loginMenuSecurity.setVisible(true);
			}
		});
		
		// Creation of the Button: "Agregar Visitante":
		RoundedButton agregarVisitanteButton = new RoundedButton("Agregar Visitante", new Color(255, 239, 91), new Color(247, 208, 57), 1000);
		agregarVisitanteButton.setBackground(new Color(255, 239, 91));
		agregarVisitanteButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 44));
		agregarVisitanteButton.setBounds(365, 228, 824, 77);
		contentPane.add(agregarVisitanteButton);
		agregarVisitanteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddVisitor addVisitorFrame = new AddVisitor();
				addVisitorFrame.setVisible(true);
				dispose(); //Close the current window	
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 10, 343, 132);
		lblNewLabel.setIcon(new ImageIcon("Media\\logo-upb-blanco1.png"));
		contentPane.add(lblNewLabel);
		
		// Creation of a JLabel containing the text: "Menú de Vigilancia":
		JLabel lblNewLabel_1 = new JLabel("Menú de Vigilancia");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Franklin Gothic Heavy", Font.BOLD, 83));
		lblNewLabel_1.setBounds(363, 34, 821, 108);
		contentPane.add(lblNewLabel_1);
		
		// // Creation of a JLabel containing the text: "Usuario: ":
		JLabel lblNewLabel_3 = new JLabel("Usuario: ");
		lblNewLabel_3.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 32));
		lblNewLabel_3.setBounds(30, 500, 154, 57);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("Media\\ImagenSeguridad.png"));
		lblNewLabel_2.setBounds(20, 143, 320, 370);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel(nameMenuSecurity);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 25));
		lblNewLabel_4.setBounds(205, 512, 629, 43);
		contentPane.add(lblNewLabel_4);
		
		RoundedPanel panel_1 = new RoundedPanel(20);
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(781, 348, 411, 85);
		contentPane.add(panel_1);
		
		RoundedPanel panel_2 = new RoundedPanel(20);
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(362, 347, 396, 85);
		contentPane.add(panel_2);
	
	} // public MenuSecurity()
} // public class MenuSecurity extends JFrame
