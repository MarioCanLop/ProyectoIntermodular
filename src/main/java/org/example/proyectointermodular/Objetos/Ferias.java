package org.example.proyectointermodular.Objetos;

import java.time.LocalDate;

public class Ferias {


    private String nombre;
    private LocalDate fehcaInicio;
    private LocalDate fechaFin;
    private String ubicacion;
    private String descripcion;

    public Ferias(String nombre, LocalDate fehcaInicio, LocalDate fechaFin, String ubicacion, String descripcion) {
        this.nombre = nombre;
        this.fehcaInicio = fehcaInicio;
        this.fechaFin = fechaFin;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFehcaInicio() {
        return fehcaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "Ferias{" +
                "nombre='" + nombre + '\'' +
                ", fehcaInicio=" + fehcaInicio +
                ", fechaFin=" + fechaFin +
                ", ubicacion='" + ubicacion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
