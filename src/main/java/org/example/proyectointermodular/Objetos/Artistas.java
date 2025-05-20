package org.example.proyectointermodular.Objetos;

public class Artistas {

    private int artistaid;
    private String nombre ;
    private String biografia;
    private String telefono ;
    private String email;

    public Artistas(int id, String nombre, String email, String telefono) {
        this.artistaid = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public Artistas(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public Artistas(int id, String nombre,  String biografia, String email, String telefono) {
        this.artistaid = id;
        this.nombre = nombre;
        this.biografia = biografia;
        this.email = email;
        this.telefono = telefono;
    }

    public Artistas(String nombre, String biografia, String email, String telefono) {
        this.nombre = nombre;
        this.biografia = biografia;
        this.email = email;
        this.telefono = telefono;
    }

    public int getArtistaid() {
        return artistaid;
    }

    public String getNombre() {
        return nombre;
    }

    public String getBiografia() {
        return biografia;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Artistas{" +
                "nombre='" + nombre + '\'' +
                ", biografia='" + biografia + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
