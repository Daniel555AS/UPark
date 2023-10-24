package co.edu.upb.upark;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Map extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Map frame = new Map();
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
	public Map() {
		
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
		
		// Creation of a JLabel with the Logo of the University:
		JLabel lblMapa= new JLabel("");
		lblMapa.setBounds(44, 152, 1184, 365);
		contentPane.add(lblMapa);
		lblMapa.setIcon(new ImageIcon("Media\\Map.png"));
		
		// Creation of a JLabel containing the text: "MAPA".
		JLabel mapaLabel = new JLabel("Nuestro Mapa");
		mapaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mapaLabel.setFont(new Font("Franklin Gothic Heavy", Font.PLAIN, 110));
		mapaLabel.setBounds(397, 30, 805, 112);
		contentPane.add(mapaLabel);
		
		// Creation of the button: "REGRESAR":
		RoundedButton regresarButton = new RoundedButton("REGRESAR", new Color(229, 86, 109), new Color(216, 58, 58), 1000);
		regresarButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 31));
		regresarButton.setForeground(new Color(0, 0, 0));
		regresarButton.setBackground(new Color(243, 37, 68));
		regresarButton.setBounds(525, 527, 221, 57);
		regresarButton.setFocusable(false);
		contentPane.add(regresarButton);
		regresarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Login ls = new Login();
				ls.setVisible(true);
				dispose(); //Close the current window

			}
		});
		
	} // public Map()

} // public class Map extends JFrame
