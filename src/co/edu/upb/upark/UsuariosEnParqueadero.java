package co.edu.upb.upark;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UsuariosEnParqueadero extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaUsuarios;

    public UsuariosEnParqueadero() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Usuarios en Parqueadero");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        tablaUsuarios = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window by clicking "Cerrar"
            }
        });
        panel.add(btnCerrar, BorderLayout.SOUTH);
    }

    public void mostrarDatosUsuarios() {
        try {
            // Establishing the connection to the database
            Connection conn = DatabaseConnection.getConnection();

            // Create an SQL query to obtain the data
            String sql = "SELECT NumeroIdentificacion, NombreUsuario, DocumentoUsuario, Placa FROM usuariosActuales";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Create a "DefaultTableModel" for the table
            DefaultTableModel model = new DefaultTableModel();
            tablaUsuarios.setModel(model);

            // Add the columns
            model.addColumn("Número de Identificación");
            model.addColumn("Nombre de Usuario");
            model.addColumn("Documento de Usuario");
            model.addColumn("Placa");

            // Add the rows with the query data
            while (resultSet.next()) {
                String numIdentificacion = resultSet.getString("NumeroIdentificacion");
                String nombreUsuario = resultSet.getString("NombreUsuario");
                String docUsuario = resultSet.getString("DocumentoUsuario");
                String placa = resultSet.getString("Placa");
                model.addRow(new Object[]{numIdentificacion, nombreUsuario, docUsuario, placa});
            }
            // Close the connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UsuariosEnParqueadero ventana = new UsuariosEnParqueadero();
            ventana.mostrarDatosUsuarios(); // Call the function to load the data on startup
            ventana.setVisible(true);
        });
    }
}
