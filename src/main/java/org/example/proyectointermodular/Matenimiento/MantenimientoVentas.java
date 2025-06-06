package org.example.proyectointermodular.Mantenimiento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.proyectointermodular.Objetos.Ventas;

import java.sql.*;
import java.time.LocalDate;

public class MantenimientoVentas {

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

    public static ObservableList<Ventas> consultar(Connection conexion) {
        String query = "SELECT precioventa, fechaventa FROM ventas";
        ObservableList<Ventas> listaVentas = FXCollections.observableArrayList();

        try (Statement stmt = conexion.createStatement();
             ResultSet resultado = stmt.executeQuery(query)) {

            while (resultado.next()) {
                int precio = resultado.getInt("precio");
                LocalDate fecha = resultado.getDate("fecha").toLocalDate();

                listaVentas.add(new Ventas(precio, fecha));
            }

        } catch (SQLException e) {
            System.err.println("Error en consulta: " + e.getMessage());
        }

        return listaVentas;
    }
}