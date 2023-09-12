package co.edu.upb.upark;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UsuariosEnParqueadero extends JFrame {
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
                dispose(); // Cierra la ventana al hacer clic en "Cerrar"
            }
        });
        panel.add(btnCerrar, BorderLayout.SOUTH);
    }

    public void mostrarDatosUsuarios() {
        try {
            // Establecer la conexión a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://:/", "***", "***");

            // Crear una consulta SQL para obtener los datos
            String sql = "SELECT NumeroIdentificacion, NombreUsuario, DocumentoUsuario, Placa FROM usuariosActuales";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Crear un DefaultTableModel para la tabla
            DefaultTableModel model = new DefaultTableModel();
            tablaUsuarios.setModel(model);

            // Agregar las columnas
            model.addColumn("Número de Identificación");
            model.addColumn("Nombre de Usuario");
            model.addColumn("Documento de Usuario");
            model.addColumn("Placa");

            // Agregar las filas con los datos de la consulta
            while (resultSet.next()) {
                String numIdentificacion = resultSet.getString("NumeroIdentificacion");
                String nombreUsuario = resultSet.getString("NombreUsuario");
                String docUsuario = resultSet.getString("DocumentoUsuario");
                String placa = resultSet.getString("Placa");
                model.addRow(new Object[]{numIdentificacion, nombreUsuario, docUsuario, placa});
            }

            // Cerrar la conexión
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UsuariosEnParqueadero ventana = new UsuariosEnParqueadero();
            ventana.mostrarDatosUsuarios(); // Llamar a la función para cargar los datos al iniciar
            ventana.setVisible(true);
        });
    }
}
