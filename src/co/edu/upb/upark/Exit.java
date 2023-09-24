package co.edu.upb.upark;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Exit extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static String nameForExit = "";

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Exit frame = new Exit();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Exit() {
        this.setResizable(false); // Desabilitar maximizar /Razon ... Dimensionar
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

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://:/", "***", "***")) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT NombreUsuario FROM usuariosActuales WHERE NumeroIdentificacion = '" + Login.IdentificationNumberExit + "'")) {
                    if (resultSet.next()) {
                        nameForExit = resultSet.getString("NombreUsuario");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JLabel lblNewLabel = new JLabel("CONFIRMACION DE DATOS PARA SALIDA");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 40));
        lblNewLabel.setBounds(188, 152, 895, 72);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(10, 10, 343, 132);
        lblNewLabel_1.setIcon(new ImageIcon("Media\\logo-upb-blanco1.png"));
        contentPane.add(lblNewLabel_1);

        //Boton para Confirmar (El color de fondo funciona solo al ejecutar)/Sin razon ..?
        JButton confirmarButton = new JButton("CONFIRMAR");
        confirmarButton.setBackground(new Color(255, 239, 91));
        confirmarButton.setForeground(new Color(172, 50, 9));
        confirmarButton.setFont(new Font("Cambria", Font.BOLD, 50));
        confirmarButton.setBounds(826, 454, 335, 82);
        contentPane.add(confirmarButton);
        

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://:/", "***", "***")) {
                    try (Statement statement = connection.createStatement()) {
                        String deleteQuery = "DELETE FROM usuariosActuales WHERE NumeroIdentificacion = '" + Login.IdentificationNumberExit + "'";
                        statement.executeUpdate(deleteQuery);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                JOptionPane optionPane = new JOptionPane("Salida confirmada. Gracias por usar nuestros servicios", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

                // Crear un JDialog sin botones
                JDialog dialog = new JDialog(Exit.this, "Confirmación", true);
                dialog.setContentPane(optionPane);

                // Establecer un temporizador para cerrar el diálogo después de 2 segundos
                Timer timer = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        dialog.dispose();
                        // Volver a la ventana de login
                        Login loginFrame = new Login();
                        loginFrame.setVisible(true);
                        dispose();
                    }
                });

                timer.setRepeats(false);
                timer.start();

                dialog.pack();
                dialog.setLocationRelativeTo(Exit.this);
                dialog.setVisible(true);
            }
        });

        JLabel lblNewLabel_2 = new JLabel(nameForExit);
        lblNewLabel_2.setFont(new Font("Cambria", Font.BOLD, 36));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(188, 257, 888, 49);
        contentPane.add(lblNewLabel_2);

        //Boton para regresar (El color de fondo funciona solo al ejecutar)/Sin razon ..?
        JButton regresarButton = new JButton("REGRESAR");
        regresarButton.setBackground(new Color(255, 239, 91));
        regresarButton.setHorizontalAlignment(SwingConstants.CENTER);
        regresarButton.setForeground(new Color(172, 50, 9));
        regresarButton.setFont(new Font("Cambria", Font.BOLD, 50));
        regresarButton.setBounds(64, 454, 385, 82);
        contentPane.add(regresarButton);

        regresarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login loginFrame = new Login();
                loginFrame.setVisible(true);  
                dispose();  
            }
        });
    }
}
