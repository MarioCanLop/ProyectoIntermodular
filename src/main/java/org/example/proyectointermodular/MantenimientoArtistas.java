package org.example.proyectointermodular;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.proyectointermodular.Objetos.Artistas;

import java.sql.*;

public class MantenimientoArtistas {

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

    public static ObservableList<Artistas> consultar() {
        String query = "SELECT * FROM artistas";
        ObservableList<Artistas> listaArtistas = FXCollections.observableArrayList();

        Connection conexion = conectar();

        try (Statement stmt = conexion.createStatement();
             ResultSet resultado = stmt.executeQuery(query)) {

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                // Otros campos que tengas en la clase Artistas
                Artistas artista = new Artistas(nombre);
                listaArtistas.add(artista);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            desconectar(conexion);
        }
        return listaArtistas;
    }

    public static void insertar(Artistas artista) {
        String query = "INSERT INTO artistas (nombre) VALUES (?)";

        Connection conexion = conectar();

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, artista.getNombre());
            // Otros campos si tienes
            ps.executeUpdate();
            System.out.println("Artista insertado correctamente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            desconectar(conexion);
        }
    }

    public static void modificar(Artistas artista) {
        String query = "UPDATE artistas SET nombre = ? WHERE id = ?";

        Connection conexion = conectar();

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, artista.getNombre());
            ps.setInt(2, artista.getId());
            ps.executeUpdate();
            System.out.println("Artista modificado correctamente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            desconectar(conexion);
        }
    }

    public static void borrar(int id) {
        String query = "DELETE FROM artistas WHERE id = ?";

        Connection conexion = conectar();

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Artista eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            desconectar(conexion);
        }
    }
}
