package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao;

import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalificacionDao {
        
    ConnSingleton conexion = ConnSingleton.getInstance();
    
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

            conn = conexion.getConnection();

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

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CalificacionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return calificaciones;
    }

    
    public List<Calificacion> obtenerCalificacionesPorMateriaGrupo(int idMateria, int idGrupo, int idMaestro) {
        List<Calificacion> calificaciones = new ArrayList<>();
        
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;
            
            consulta = "SELECT c.id, u.nombre AS nombre_alumno, c.calificacion "
                + "FROM Calificaciones c "
                + "JOIN Alumnos al ON c.id_alumno = al.id "
                + "JOIN Usuarios u ON al.id_usuario = u.id "
                + "JOIN MaestroMateriaGrupo mmg ON c.id_asignacion = mmg.id "
                + "WHERE mmg.id_materia = ? AND mmg.id_grupo = ? AND mmg.id_maestro = ? AND al.id_grupo = mmg.id_grupo";
            
            conn = conexion.getConnection();
            
            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idMateria);
            pst.setInt(2, idGrupo);
            pst.setInt(3, idMaestro);
            
            rs = pst.executeQuery();

            while (rs.next()) {
                Calificacion calificacion = new Calificacion();
                calificacion.setId(rs.getInt("id"));
                calificacion.setNombreAlumno(rs.getString("nombre_alumno"));
                calificacion.setCalificacion(rs.getDouble("calificacion"));
                calificaciones.add(calificacion);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return calificaciones;
    }

    
    public boolean registrarCalificacion(int idAlumno, int idGrupo, int idMaestro, int idMateria, double calificacion){
        boolean resultado = false;
        
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;
            
            
            consulta = "INSERT INTO calificaciones (id_alumno, id_asignacion, calificacion) " +
                          "VALUES (?, (SELECT id FROM MaestroMateriaGrupo WHERE id_materia = ? AND id_grupo = ? AND id_maestro = ? LIMIT 1), ?)";
            
            conn = conexion.getConnection();
            
            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idAlumno);
            pst.setInt(2, idMateria);
            pst.setInt(3, idGrupo);
            pst.setInt(4, idMaestro);
            pst.setDouble(5, calificacion);

            int filasAfectadas = pst.executeUpdate();
            resultado = filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
}

