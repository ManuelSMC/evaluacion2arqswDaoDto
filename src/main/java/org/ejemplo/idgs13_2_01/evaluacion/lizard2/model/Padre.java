package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model;

public class Padre {
    private int id;
    private UsuarioModel usuario;

    public Padre() {
    }

    public Padre(int id, UsuarioModel usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    
}

