package org.example.proyectointermodular.Objetos;

import java.time.LocalDate;

public class Ventas {

    private int Precio;
    private LocalDate fecha ;

    public Ventas(int precio, LocalDate fecha) {
        Precio = precio;
        this.fecha = fecha;
    }

    public int getPrecio() {
        return Precio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Ventas{" +
                "Precio=" + Precio +
                ", fecha=" + fecha +
                '}';
    }
}
