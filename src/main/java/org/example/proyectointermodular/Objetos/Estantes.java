package org.example.proyectointermodular.Objetos;

public class Estantes {

    private String nombre;

    public Estantes(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Estantes{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
