package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calificacion {
    private int id;
    private int idAlumno;
    private int idAsignacion;
    private double calificacion;
    private String nombreAlumno;
    private String nombreMateria;
    private Materia materia;

    public Calificacion() {}

    public Calificacion(int id, int idAlumno, int idAsignacion, double calificacion, String nombreAlumno, String nombreMateria) {
        this.id = id;
        this.idAlumno = idAlumno;
        this.idAsignacion = idAsignacion;
        this.calificacion = calificacion;
        this.nombreAlumno = nombreAlumno;
        this.nombreMateria = nombreMateria;
    }

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

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    
    
    
    
    public List<Calificacion> getCalificacionGrupo(int idGrupo) {
        List<Calificacion> calificaciones = new ArrayList<>();
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT c.id AS id_calificacion, c.id_alumno, u.nombre AS nombre_alumno, "
                    + "c.id_asignacion, m.nombre AS nombre_materia, c.calificacion "
                    + "FROM Calificaciones c "
                    + "JOIN Alumnos a ON c.id_alumno = a.id "
                    + "JOIN Usuarios u ON a.id_usuario = u.id "
                    + "JOIN MaestroMateriaGrupo mmg ON c.id_asignacion = mmg.id "
                    + "JOIN Materias m ON mmg.id_materia = m.id "
                    + "WHERE mmg.id_grupo = ?";

            // Cargar driver y conectar
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/evaluacion2?useSSL=false&allowPublicKeyRetrieval=true", "root", "Perfect97");

            pst = conn.prepareStatement(consulta);
            pst.setInt(1, idGrupo);
            rs = pst.executeQuery();

            while (rs.next()) {
                Calificacion calificacion = new Calificacion(
                        rs.getInt("id_calificacion"),
                        rs.getInt("id_alumno"),
                        rs.getInt("id_asignacion"),
                        rs.getDouble("calificacion"),
                        rs.getString("nombre_alumno"),
                        rs.getString("nombre_materia")
                );
                calificaciones.add(calificacion);
            }

            conn.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Calificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return calificaciones;
    }


    
}

