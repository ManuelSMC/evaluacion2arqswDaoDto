package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model;

public class Grupo {
    private int id;
    private String nombre;
    private Carrera carrera;

    public Grupo() {
    }

    public Grupo(int id, String nombre, Carrera carrera) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
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

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    
}