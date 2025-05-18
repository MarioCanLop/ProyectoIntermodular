package org.example.proyectointermodular.Matenimiento;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.proyectointermodular.Objetos.Ventas;

import java.sql.*;
import java.time.LocalDate;

public class MantenimientoVentas {

    private static final String HOST = "jdbc:mariadb://localhost:3307/";
    private static final String BD = "instituto"; // Ajusta según tu BD
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    private static Connection conectar() {
        try {
            return DriverManager.getConnection(HOST + BD, USUARIO, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error conectando a la base de datos.");
        }
    }

    private static void desconectar(Connection conexion) {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión finalizada.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static ObservableList<Ventas> consultar() {
        String query = "SELECT * FROM ventas";  // Cambia "ventas" por el nombre real de tu tabla
        ObservableList<Ventas> listaVentas = FXCollections.observableArrayList();

        Connection conexion = conectar();

        try (Statement stmt = conexion.createStatement();
             ResultSet resultado = stmt.executeQuery(query)) {

            while (resultado.next()) {
                int id = resultado.getInt("id");  // Suponiendo que tienes un campo id autoincremental
                int precio = resultado.getInt("precio");
                LocalDate fecha = resultado.getDate("fecha").toLocalDate();

                Ventas venta = new Ventas(precio, fecha);
                // Necesitamos almacenar el ID para editar/borrar después.
                // Puedes implementar un setter o usar un Map en el controlador.
                listaVentas.add(venta);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            desconectar(conexion);
        }
        return listaVentas;
    }

    public static void insertar(Ventas venta) {
        String query = "INSERT INTO ventas (precio, fecha) VALUES (?, ?)";

        Connection conexion = conectar();

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, venta.getPrecio());
            ps.setDate(2, Date.valueOf(venta.getFecha()));
            ps.executeUpdate();
            System.out.println("Venta insertada correctamente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            desconectar(conexion);
        }
    }

    public static void modificar(Ventas venta, int id) {
        String query = "UPDATE ventas SET precio = ?, fecha = ? WHERE id = ?";

        Connection conexion = conectar();

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, venta.getPrecio());
            ps.setDate(2, Date.valueOf(venta.getFecha()));
            ps.setInt(3, id);
            ps.executeUpdate();
            System.out.println("Venta modificada correctamente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            desconectar(conexion);
        }
    }

    public static void borrar(Ventas venta, int id) {
        String query = "DELETE FROM ventas WHERE id = ?";

        Connection conexion = conectar();

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Venta eliminada correctamente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            desconectar(conexion);
        }
    }

    public static int obtenerId(Ventas venta) {
        // Aquí dependerá de cómo guardes el ID, o puedes extender la clase Ventas para tener id
        // En este ejemplo no está implementado, lo ideal es que Ventas tenga un campo id.
        return -1;
    }
}


