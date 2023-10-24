package co.edu.upb.upark;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class HistorialVentana extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaHistorial;

    public HistorialVentana() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Historial");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        tablaHistorial = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaHistorial);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
        panel.add(btnCerrar, BorderLayout.SOUTH);
    }

    public void mostrarDatosHistorial() {
        try {
            // Establishing the connection to the database
            Connection conn = DatabaseConnection.getConnection();

            // Create an SQL query to obtain the data
            String sql = "SELECT id, NumeroIdentificacion, NombreUsuario, DocumentoUsuario, Placa, entrada, salida FROM historial";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            tablaHistorial.setModel(model);

            model.addColumn("ID");
            model.addColumn("Número de Identificación");
            model.addColumn("Nombre de Usuario");
            model.addColumn("Documento de Usuario");
            model.addColumn("Placa");
            model.addColumn("Entrada");
            model.addColumn("Salida");

            // Add rows (all)
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String numIdentificacion = resultSet.getString("NumeroIdentificacion");
                String nombreUsuario = resultSet.getString("NombreUsuario");
                String docUsuario = resultSet.getString("DocumentoUsuario");
                String placa = resultSet.getString("Placa");
                String entrada = resultSet.getString("entrada");
                String salida = resultSet.getString("salida");
                model.addRow(new Object[]{id, numIdentificacion, nombreUsuario, docUsuario, placa, entrada, salida});
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // mostrarDatosHistorial()

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HistorialVentana ventana = new HistorialVentana();
            ventana.mostrarDatosHistorial(); 
            ventana.setVisible(true);
        });
    } // main
} // public class HistorialVentana extends JFrame 
