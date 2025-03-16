package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model;

public class Maestro {
    private int id;
    private UsuarioModel usuario;

    public Maestro() {
    }
    
    public Maestro(int id, UsuarioModel usuario) {
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

