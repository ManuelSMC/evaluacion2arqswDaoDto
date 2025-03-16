package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model;

public class Grupo {
    private int id;
    private String nombre;
    private int idCarrera;

    public Grupo() {
    }

    public Grupo(int id, String nombre, int idCarrera) {
        this.id = id;
        this.nombre = nombre;
        this.idCarrera = idCarrera;
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

    public int getCarrera() {
        return idCarrera;
    }

    public void setCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    
}