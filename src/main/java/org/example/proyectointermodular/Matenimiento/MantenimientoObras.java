package org.example.proyectointermodular.Matenimiento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.proyectointermodular.Objetos.ObrasDeArte;

import java.sql.*;

public class MantenimientoObras {

    private static final String HOST = "jdbc:mariadb://localhost:3307/";
    private static final String BD = "instituto";
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

    public static ObservableList<ObrasDeArte> consultar() {
        String query = "SELECT * FROM obras_de_arte"; // Cambia según el nombre real de tu tabla
        ObservableList<ObrasDeArte> listaObras = FXCollections.observableArrayList();

        Connection conexion = conectar();

        try (Statement stmt = conexion.createStatement();
             ResultSet resultado = stmt.executeQuery(query)) {

            while (resultado.next()) {
                int id = resultado.getInt("id"); // Asumiendo que tienes un campo ID
                String titulo = resultado.getString("titulo");
                String descripcion = resultado.getString("descripcion");
                String disponible = resultado.getString("disponible");
                int precio = resultado.getInt("precio");

                ObrasDeArte obra = new ObrasDeArte(titulo, descripcion, disponible, precio);
                // Puedes agregar método setId si tienes ID en ObrasDeArte para manejarlo
                listaObras.add(obra);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            desconectar(conexion);
        }
        return listaObras;
    }

    public static void insertar(ObrasDeArte obra) {
        String query = "INSERT INTO obras_de_arte (titulo, descripcion, disponible, precio) VALUES (?, ?, ?, ?)";

        Connection conexion = conectar();

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, obra.getTitulo());
            ps.setString(2, obra.getDescripcion());
            ps.setString(3, obra.getDisponible());
            ps.setInt(4, obra.getPrecio());
            ps.executeUpdate();
            System.out.println("Obra insertada correctamente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            desconectar(conexion);
        }
    }

    public static void modificar(ObrasDeArte obra, int id) {
        String query = "UPDATE obras_de_arte SET titulo = ?, descripcion = ?, disponible = ?, precio = ? WHERE id = ?";

        Connection conexion = conectar();

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, obra.getTitulo());
            ps.setString(2, obra.getDescripcion());
            ps.setString(3, obra.getDisponible());
            ps.setInt(4, obra.getPrecio());
            ps.setInt(5, id);
            ps.executeUpdate();
            System.out.println("Obra modificada correctamente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            desconectar(conexion);
        }
    }

    public static void borrar(int id) {
        String query = "DELETE FROM obras_de_arte WHERE id = ?";

        Connection conexion = conectar();

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Obra eliminada correctamente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            desconectar(conexion);
        }
    }
}

