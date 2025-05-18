package org.example.proyectointermodular.Objetos;

import java.time.LocalDate;

public class Entradas {

    private LocalDate fecha;
    private int precio;

    public Entradas(LocalDate fecha, int precio) {
        this.fecha = fecha;
        this.precio = precio;
    }

    public int getPrecio() {
        return precio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Entradas{" +
                "fecha=" + fecha +
                ", precio=" + precio +
                '}';
    }
}
