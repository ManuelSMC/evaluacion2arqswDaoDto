package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model;

public class MaestroMateriaGrupo {
    private int id;
    private int idMaestro;
    private int idMateria;
    private int idGrupo;

    public MaestroMateriaGrupo() {
    }

    public MaestroMateriaGrupo(int id, int idMaestro, int idMateria, int idGrupo) {
        this.id = id;
        this.idMaestro = idMaestro;
        this.idMateria = idMateria;
        this.idGrupo = idGrupo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(int idMaestro) {
        this.idMaestro = idMaestro;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }
    
}
