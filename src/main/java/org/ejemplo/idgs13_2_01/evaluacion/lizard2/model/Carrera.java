package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model;

public class Carrera {
    private int id;
    private String nombre;

    public Carrera() {
    }
    
    public Carrera(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}

