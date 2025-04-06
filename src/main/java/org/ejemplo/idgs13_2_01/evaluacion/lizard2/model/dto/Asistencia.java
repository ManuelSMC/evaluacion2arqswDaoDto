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

public class Asistencia {
    private int id;
    private int idAlumno;
    private int idAsignacion;
    private String fecha;
    private String estado;
    private String nombreAlumno;

    public Asistencia() {
    }

    public Asistencia(int id, int idAlumno, int idAsignacion, String fecha, String estado) {
        this.id = id;
        this.idAlumno = idAlumno;
        this.idAsignacion = idAsignacion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Asistencia(int id, int idAlumno, int idAsignacion, String fecha, String estado, String nombreAlumno) {
        this.id = id;
        this.idAlumno = idAlumno;
        this.idAsignacion = idAsignacion;
        this.fecha = fecha;
        this.estado = estado;
        this.nombreAlumno = nombreAlumno;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(int idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }
    
}

