package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model;

public class MaestroMateriaGrupo {
    private int id;
    private Maestro maestro;
    private Materia materia;
    private Grupo grupo;

    public MaestroMateriaGrupo() {
    }

    public MaestroMateriaGrupo(int id, Maestro maestro, Materia materia, Grupo grupo) {
        this.id = id;
        this.maestro = maestro;
        this.materia = materia;
        this.grupo = grupo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Maestro getMaestro() {
        return maestro;
    }

    public void setMaestro(Maestro maestro) {
        this.maestro = maestro;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    
}
