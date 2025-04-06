package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grupo {
    private int id;
    private String nombre;
    private int idCarrera;
    private Carrera Carrera;

    public Grupo() {
    }

    public Grupo(int id, String nombre, int idCarrera) {
        this.id = id;
        this.nombre = nombre;
        this.idCarrera = idCarrera;
    }

    public Grupo(int id, String nombre, int idCarrera, Carrera Carrera) {
        this.id = id;
        this.nombre = nombre;
        this.idCarrera = idCarrera;
        this.Carrera = Carrera;
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

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public Carrera getCarrera() {
        return Carrera;
    }

    public void setCarrera(Carrera Carrera) {
        this.Carrera = Carrera;
    }
}