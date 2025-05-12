package org.example.proyectointermodular.Objetos;

public class Artistas {

    private String nombre ;
    private String biografia;
    private String telefono ;
    private String email;

    public Artistas(String nombre, String biografia, String telefono, String email) {
        this.nombre = nombre;
        this.biografia = biografia;
        this.telefono = telefono;
        this.email = email;
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
}
