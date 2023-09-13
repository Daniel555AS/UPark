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

public class Exit extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Exit frame = new Exit();
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
	public Exit() {

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

		String nameEx = "";

		// --------------------------- Get The Name Of The Vehicle Owner ---------------------------

		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://35.222.147.13:3306/parqueadero", "root", "842963");
			Statement stmtExit = conn.createStatement();
			ResultSet rsExit = stmtExit.executeQuery("SELECT numeroidentificacion FROM usuariosActuales");
			ArrayList<String> listExit = new ArrayList<>();
			while (rsExit.next()) {
				String valor = rsExit.getString("numeroidentificacion");
				listExit.add(valor);
			}
			String[] arrayExit= listExit.toArray(new String[0]);
			rsExit.close();
			stmtExit.close();

			Statement stmtExitNames = conn.createStatement();
			ResultSet rsExitNames = stmtExitNames.executeQuery("SELECT nombreusuario FROM usuariosActuales");
			ArrayList<String> listExitNames = new ArrayList<>();
			while (rsExitNames.next()) {
				String valor = rsExitNames.getString("nombreusuario");
				listExitNames.add(valor);
			}
			String[] arrayExitNames = listExitNames.toArray(new String[0]);
			rsExitNames.close();
			stmtExitNames.close();

			int positionForName = 0;

			for(int ii = 0; ii < arrayExit.length; ii++) {
				if(arrayExit[ii] == Login.IdentificationNumber){
					positionForName = ii;
					break;
				}
			}//for

			nameEx = arrayExitNames[positionForName];

		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		// -----------------------------------------------------------------------------------------

		JLabel lblNewLabel = new JLabel("SALIDA EXITOSA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 99));
		lblNewLabel.setBounds(188, 153, 895, 124);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 10, 343, 132);
		lblNewLabel_1.setIcon(new ImageIcon("Media\\logo-upb-blanco1.png"));
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("¡Gracias Por Usar Nuestro Servicio!");
		lblNewLabel_3.setForeground(new Color(172, 50, 9));
		lblNewLabel_3.setFont(new Font("Cambria", Font.BOLD, 70));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(64, 410, 1144, 82);
		contentPane.add(lblNewLabel_3);

	}

}
