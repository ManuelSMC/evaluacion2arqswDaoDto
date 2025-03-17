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

public class Alumno {
    private int id;
    private int idUsuario;
    private int idGrupo;
    private int idPadre;

    public Alumno() {
    }

    public Alumno(int id, int idUsuario, int idGrupo, int idPadre) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idGrupo = idGrupo;
        this.idPadre = idPadre;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdGrupo() { return idGrupo; }
    public void setIdGrupo(int idGrupo) { this.idGrupo = idGrupo; }

    public int getIdPadre() { return idPadre; }
    public void setIdPadre(int idPadre) { this.idPadre = idPadre; }

    public Alumno getById(int idUsuario) {
        
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM alumnos WHERE id_usuario = ?";
            
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/evaluacion2?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "Perfect97");

            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idUsuario);
            rs = pst.executeQuery();

            if (rs.next()) {
                Alumno alumno = new Alumno(
                        rs.getInt("id"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_grupo"),
                        rs.getInt("id_padre")
                );
                return alumno;
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Calificacion> obtenerCalificaciones(int id) {
        List<Calificacion> calificaciones = new ArrayList<>();
        

        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT c.id, c.id_alumno, c.id_asignacion, c.calificacion, "
                + "m.id AS materia_id, m.nombre AS materia_nombre "
                + "FROM Calificaciones c "
                + "JOIN MaestroMateriaGrupo mmg ON c.id_asignacion = mmg.id "
                + "JOIN Materias m ON mmg.id_materia = m.id "
                + "WHERE c.id_alumno = ?";
            
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/evaluacion2?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "Perfect97");
            pst = conn.prepareStatement(consulta);

            pst.setInt(1, this.id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia(rs.getInt("materia_id"), rs.getString("materia_nombre"));
                Calificacion calificacion = new Calificacion(
                        rs.getInt("id"),
                        rs.getInt("id_alumno"),
                        rs.getInt("id_asignacion"),
                        rs.getDouble("calificacion"),
                        materia
                );
                calificaciones.add(calificacion);
               
            }
            return calificaciones;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }

    public List<Asistencia> obtenerAsistencias(int idUsuario, int idMateria) {
        List<Asistencia> asistencias = new ArrayList<>();

        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT a.id, a.id_alumno, a.id_asignacion, a.fecha, a.estado "
                + "FROM Asistencias a "
                + "JOIN MaestroMateriaGrupo mmg ON a.id_asignacion = mmg.id "
                + "JOIN Materias m ON mmg.id_materia = m.id "
                + "WHERE a.id_alumno = ? AND m.id = ?";
            
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/evaluacion2?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "Perfect97");
            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idUsuario);
            pst.setInt(2, idMateria);
            rs = pst.executeQuery();
            while (rs.next()) {
                Asistencia asistencia = new Asistencia(
                        rs.getInt("id"),
                        rs.getInt("id_alumno"),
                        rs.getInt("id_asignacion"),
                        rs.getString("fecha"),
                        rs.getString("estado")
                );
                asistencias.add(asistencia);

            }
            return asistencias;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
