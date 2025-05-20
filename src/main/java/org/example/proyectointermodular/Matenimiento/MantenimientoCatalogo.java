package org.example.proyectointermodular.Matenimiento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.proyectointermodular.Objetos.Catalogo;

import java.sql.*;

public class MantenimientoCatalogo {

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
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al desconectar: " + e.getMessage());
        }
    }

    public static ObservableList<Catalogo> consultar(Connection conexion) {
        String query = "SELECT * FROM catalogos";
        ObservableList<Catalogo> listaCatalogo = FXCollections.observableArrayList();

        try (Statement stmt = conexion.createStatement();
             ResultSet resultado = stmt.executeQuery(query)) {

            while (resultado.next()) {
                String nombre = resultado.getString("nombre");
                String descripcion = resultado.getString("descripcion");

                listaCatalogo.add(new Catalogo(nombre, descripcion));
            }

        } catch (SQLException e) {
            System.err.println("Error en consulta: " + e.getMessage());
        }

        return listaCatalogo;
    }
}