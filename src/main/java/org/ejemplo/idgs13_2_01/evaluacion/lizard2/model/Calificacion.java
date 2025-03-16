package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model;

public class Calificacion {
    private int id;
    private int idAlumno;
    private int idAsignacion;
    private double calificacion;
    private Materia materia;

    public Calificacion() {}

    public Calificacion(int id, int idAlumno, int idAsignacion, double calificacion, Materia materia) {
        this.id = id;
        this.idAlumno = idAlumno;
        this.idAsignacion = idAsignacion;
        this.calificacion = calificacion;
        this.materia = materia;
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

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    
}

