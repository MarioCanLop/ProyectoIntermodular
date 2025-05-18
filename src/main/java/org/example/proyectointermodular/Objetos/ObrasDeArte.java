package org.example.proyectointermodular.Objetos;

public class ObrasDeArte {

    private String titulo;
    private String descripcion;
    private String disponible;
    private int Precio;

    public ObrasDeArte(String titulo, String descripcion, String disponible, int precio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.disponible = disponible;
        Precio = precio;
    }


    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDisponible() {
        return disponible;
    }

    public int getPrecio() {
        return Precio;
    }

    @Override
    public String toString() {
        return "ObrasDeArte{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", disponible='" + disponible + '\'' +
                ", Precio=" + Precio +
                '}';
    }
}
