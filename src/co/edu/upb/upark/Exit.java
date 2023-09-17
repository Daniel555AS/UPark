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

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String nameForExit = "";

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



		// --------------------------- Get The Name Of The Vehicle Owner ---------------------------

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://35.222.147.13:3306/parqueadero", "root", "842963")) {
            //Create a SQL statement
            try (Statement statement = connection.createStatement()) {
                //Run the query and get the result
                try (ResultSet resultSet = statement.executeQuery("SELECT NombreUsuario FROM usuariosActuales WHERE NumeroIdentificacion = '" + Login.IdentificationNumberExit + "'")) {
                   
                    if (resultSet.next()) {
                        nameForExit = resultSet.getString("NombreUsuario");
 
                    } 
                }
            }
        } catch (SQLException e) {
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
		
		JLabel lblNewLabel_2 = new JLabel(nameForExit);
		lblNewLabel_2.setFont(new Font("Cambria", Font.BOLD, 36));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(188, 335, 888, 49);
		contentPane.add(lblNewLabel_2);

	}
}
