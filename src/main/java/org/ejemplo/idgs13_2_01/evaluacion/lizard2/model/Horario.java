package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model;

public class Horario {
    private int id;
    private Grupo grupo;
    private Materia materia;
    private String dia;
    private String horaInicio;
    private String horaFin;

    public Horario() {
    }

    public Horario(int id, Grupo grupo, Materia materia, String dia, String horaInicio, String horaFin) {
        this.id = id;
        this.grupo = grupo;
        this.materia = materia;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

}

