package co.edu.upb.upark;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MenuSecurity extends JFrame {

	private JPanel contentPane;

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
		
		JButton btnNewButton = new JButton("Usuarios en Parqueadero");
		btnNewButton.setBackground(new Color(162, 214, 22));
		btnNewButton.setFont(new Font("Cambria", Font.BOLD, 30));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(785, 352, 404, 77);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Historial");
		btnNewButton_1.setBackground(new Color(162, 214, 22));
		btnNewButton_1.setFont(new Font("Cambria", Font.BOLD, 35));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(365, 351, 389, 77);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cerrar Sesión");
		btnNewButton_2.setBackground(new Color(243, 37, 68));
		btnNewButton_2.setForeground(new Color(0, 0, 0));
		btnNewButton_2.setFont(new Font("Cambria", Font.BOLD, 35));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(858, 489, 389, 77);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Agregar Visitante");
		btnNewButton_3.setBackground(new Color(255, 239, 91));
		btnNewButton_3.setFont(new Font("Cambria", Font.BOLD, 44));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AddVisitor addVisitorFrame = new AddVisitor();
				addVisitorFrame.setVisible(true);
				dispose(); //Close the current window	
				
			}
		});
		btnNewButton_3.setBounds(365, 228, 824, 77);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 10, 343, 132);
		lblNewLabel.setIcon(new ImageIcon("Media\\logo-upb-blanco1.png"));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Menú de Vigilancia");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD, 90));
		lblNewLabel_1.setBounds(363, 34, 821, 108);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Usuario: ");
		lblNewLabel_3.setFont(new Font("Cambria", Font.BOLD, 32));
		lblNewLabel_3.setBounds(34, 509, 131, 57);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("Media\\ImagenSeguridad.png"));
		lblNewLabel_2.setBounds(20, 143, 320, 370);
		contentPane.add(lblNewLabel_2);
		
		
		String nameSecurity = LoginSecurity.name;
		
		JLabel lblNewLabel_4 = new JLabel(nameSecurity);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setFont(new Font("Cambria", Font.BOLD, 20));
		lblNewLabel_4.setBounds(175, 509, 673, 43);
		contentPane.add(lblNewLabel_4);
	}
}
