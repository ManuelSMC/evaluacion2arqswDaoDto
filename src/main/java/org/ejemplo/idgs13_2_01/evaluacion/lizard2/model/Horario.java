package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model;

public class Horario {
    private int id;
    private int idGrupo;
    private int idMateria;
    private String dia;
    private String horaInicio;
    private String horaFin;

    public Horario() {
    }

    public Horario(int id, int idGrupo, int idMateria, String dia, String horaInicio, String horaFin) {
        this.id = id;
        this.idGrupo = idGrupo;
        this.idMateria = idMateria;
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

    public int getGrupo() {
        return idGrupo;
    }

    public void setGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getMateria() {
        return idMateria;
    }

    public void setMateria(int idMateria) {
        this.idMateria = idMateria;
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

