package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Alumno {
    private int id;
    private int idUsuario;
    private int idGrupo;
    private int idPadre;
    private UsuarioModel usuario;

    public Alumno() {
    }

    public Alumno(int id, int idUsuario, int idGrupo, int idPadre) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idGrupo = idGrupo;
        this.idPadre = idPadre;
    }

    public Alumno(int id, int idUsuario, int idGrupo, int idPadre, UsuarioModel usuario) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idGrupo = idGrupo;
        this.idPadre = idPadre;
        this.usuario = usuario;
    }

    public int getId() {
        return id; 
    }
    public void setId(int id) {
        this.id = id; 
    }

    public int getIdUsuario() {
        return idUsuario; 
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario; 
    }

    public int getIdGrupo() {
        return idGrupo; 
    }
    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo; 
    }

    public int getIdPadre() {
        return idPadre; 
    }
    public void setIdPadre(int idPadre) {
        this.idPadre = idPadre; 
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
    
}
