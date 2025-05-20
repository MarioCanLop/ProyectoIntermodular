package org.example.proyectointermodular.Matenimiento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.proyectointermodular.Objetos.Ferias;

import java.sql.*;
import java.time.LocalDate;

public class MantenimientoFerias {

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

    public static ObservableList<Ferias> consultar(Connection conexion) {
        String query = "SELECT nombre,fechainicio,fechafin,ubicacion,descripcion FROM ferias";
        ObservableList<Ferias> listaFerias = FXCollections.observableArrayList();

        try (Statement stmt = conexion.createStatement();
             ResultSet resultado = stmt.executeQuery(query)) {

            while (resultado.next()) {
                String nombre = resultado.getString("nombre");
                LocalDate fechaInicio = resultado.getDate("fecha_inicio").toLocalDate();
                LocalDate fechaFin = resultado.getDate("fecha_fin").toLocalDate();
                String ubicacion = resultado.getString("ubicacion");
                String descripcion = resultado.getString("descripcion");

                listaFerias.add(new Ferias(nombre, fechaInicio, fechaFin, ubicacion, descripcion));
            }

        } catch (SQLException e) {
            System.err.println("Error en consulta: " + e.getMessage());
        }

        return listaFerias;
    }
}