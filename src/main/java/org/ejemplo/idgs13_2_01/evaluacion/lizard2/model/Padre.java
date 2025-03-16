package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model;

public class Padre {
    private int id;
    private int idUsuario;

    public Padre() {
    }

    public Padre(int id, int idUsuario) {
        this.id = id;
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuario() {
        return idUsuario;
    }

    public void setUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    
}

