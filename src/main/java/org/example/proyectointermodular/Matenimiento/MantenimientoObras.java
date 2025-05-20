package org.example.proyectointermodular.Matenimiento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.proyectointermodular.Objetos.ObrasDeArte;

import java.sql.*;

public class MantenimientoObras {

    public static Connection conectar(Connection conexion) {

        String host = "jdbc:mariadb://localhost:3306/";
        String usuario = "root";
        String psw = "";
        String bd = "proyecto";

        try {
            conexion = DriverManager.getConnection(host + bd, usuario, psw);
            System.out.println("Conexión establecida correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar: " + e.getMessage());
        }

        return conexion;
    }

    public static void desconectar(Connection conexion) {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al desconectar: " + e.getMessage());
        }
    }

    public static ObservableList<ObrasDeArte> consultar(Connection conexion) {
        String query = "SELECT * FROM obras";
        ObservableList<ObrasDeArte> listaObras = FXCollections.observableArrayList();

        try (Statement stmt = conexion.createStatement();
             ResultSet resultado = stmt.executeQuery(query)) {

            while (resultado.next()) {
                String titulo = resultado.getString("titulo");
                String descripcion = resultado.getString("descripcion");
                String disponible = resultado.getString("disponible");
                int precio = resultado.getInt("precio");

                listaObras.add(new ObrasDeArte(titulo, descripcion, disponible, precio));
            }

        } catch (SQLException e) {
            System.err.println("Error en consulta: " + e.getMessage());
        }

        return listaObras;
    }
}