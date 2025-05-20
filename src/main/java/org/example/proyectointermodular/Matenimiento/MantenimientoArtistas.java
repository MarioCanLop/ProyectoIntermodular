package org.example.proyectointermodular.Matenimiento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.proyectointermodular.Objetos.Artistas;

import java.sql.*;

public class MantenimientoArtistas {

    public static ObservableList<Artistas> consultar(Connection BD) {
        String query = "SELECT * FROM artistas";
        ObservableList<Artistas> listaArtistas = FXCollections.observableArrayList();

        try (Statement stmt = BD.createStatement();
             ResultSet resultado = stmt.executeQuery(query)) {

            while (resultado.next()) {
                int id = resultado.getInt("artistaid");
                String nombre = resultado.getString("nombre");
                String email = resultado.getString("email");
                String telfeono = resultado.getString("telefono");
                Artistas artista = new Artistas(id, nombre, telfeono, email);
                listaArtistas.add(artista);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return listaArtistas;
    }

    public static void insertar(Connection BD, Artistas artista) {

        StringBuilder query = new StringBuilder();

        query.append("INSERT INTO artistas (artistaid, nombre, email, telefono) VALUES ('");
        query.append(artista.getArtistaid() + "'");
        query.append(", '" + artista.getNombre() + "'");

        if (artista.getBiografia() != null){
            query.append(", '" + artista.getBiografia() + "'");
        }

        query.append(", '" + artista.getEmail() + "'");
        query.append(", '" + artista.getTelefono() + "')");

        Statement stmt;
        try {
            stmt = BD.createStatement();
            stmt.executeUpdate(query.toString());
            System.out.println("--- FILA INSERTADA ---");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void modificar(Connection BD, Artistas artista, int id_antes) {
        String query;

        if (artista.getBiografia() == null) {
            query = ("UPDATE artistas set artistaid = '" + artista.getArtistaid() + "', nombre = '" + artista.getNombre() + "', email = '"
                    + artista.getEmail() + "', telefono = '" + artista.getTelefono() + "' WHERE artistaid = '" + id_antes + "'");
        }else{
            query = ("UPDATE artistas set artistaid = '" + artista.getArtistaid() + "', nombre = '" + artista.getNombre() + ", biografia ='" + artista.getBiografia() + "', email = '"
                    + artista.getEmail() + "', telefono = '" + artista.getTelefono() + "' WHERE artistaid = '" + id_antes + "'");
        }

        Statement stmt;
        try {
            stmt = BD.createStatement();
            stmt.executeUpdate(query);
            System.out.println("--- FILA MODIFICADA ---");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void borrar(Connection BD, int id) {

        String query = ("DELETE FROM artistas WHERE artistaid = '" + id + "'");

        Statement stmt;
        try {
            stmt = BD.createStatement();
            stmt.executeUpdate(query);
            System.out.println("--- FILA BORRADA ---");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}