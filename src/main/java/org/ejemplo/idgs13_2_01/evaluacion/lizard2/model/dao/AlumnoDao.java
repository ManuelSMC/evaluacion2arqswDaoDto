package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao;

import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlumnoDao {
        
    ConnSingleton conexion = ConnSingleton.getInstance();

    public Alumno getById(int idUsuario) {
        
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM alumnos WHERE id_usuario = ?";
            
            conn = conexion.getConnection();

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
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoDao.class.getName()).log(Level.SEVERE, null, ex);
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

            consulta = "SELECT DISTINCT "
                    + "    m.id AS id_materia, "
                    + "    m.nombre AS nombre_materia, "
                    + "    c.calificacion "
                    + "FROM Alumnos a "
                    + "JOIN Grupos g ON a.id_grupo = g.id "
                    + "JOIN MaestroMateriaGrupo mmg ON g.id = mmg.id_grupo "
                    + "JOIN Materias m ON mmg.id_materia = m.id "
                    + "LEFT JOIN Calificaciones c ON c.id_asignacion = mmg.id AND c.id_alumno = a.id "
                    + "WHERE a.id = ?";
            
            conn = conexion.getConnection();
            
            pst = conn.prepareStatement(consulta);

            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia(rs.getInt("id_materia"),
                rs.getString("nombre_materia"));
                Calificacion calificacion = new Calificacion();
                calificacion.setMateria(materia);
                calificacion.setCalificacion(rs.getDouble("calificacion"));
                calificaciones.add(calificacion);
            }
            return calificaciones;
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoDao.class.getName()).log(Level.SEVERE, null, ex);
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
            
            conn = conexion.getConnection();
            
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
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public Alumno getByIdPadre(int idPadre) {
        
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM alumnos WHERE id_padre = ?";
            
            conn = conexion.getConnection();

            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idPadre);
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
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Alumno> getAlumnosGrupoMaestro(int idGrupo, int idMaestro, int idMateria) {
        List<Alumno> listaAlumnos = new ArrayList<>();

        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT DISTINCT a.id, a.id_usuario, a.id_grupo, a.id_padre "
                    + "FROM Alumnos a "
                    + "JOIN MaestroMateriaGrupo mmg ON a.id_grupo = mmg.id_grupo "
                    + "WHERE mmg.id_grupo = ? AND mmg.id_maestro = ? AND mmg.id_materia = ?";

            conn = conexion.getConnection();

            pst = conn.prepareStatement(consulta);
            pst.setInt(1, idGrupo);
            pst.setInt(2, idMaestro);
            pst.setInt(3, idMateria);
            rs = pst.executeQuery();
            
            
            while (rs.next()) {
                UsuarioModelDao usuario = new UsuarioModelDao();
                Alumno alumno = new Alumno();
                alumno.setId(rs.getInt("id"));
                alumno.setIdUsuario(rs.getInt("id_usuario"));
                alumno.setIdGrupo(rs.getInt("id_grupo"));
                alumno.setIdPadre(rs.getInt("id_padre"));
                alumno.setUsuario(usuario.findById(rs.getInt("id_usuario")));
                listaAlumnos.add(alumno);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaAlumnos;
    }

    public void addAlumno(int idUsuario, int idGrupo) {
        String consulta = "INSERT INTO alumnos (id_usuario, id_grupo) VALUES (?, ?)";

        try (Connection conn = conexion.getConnection(); 
        PreparedStatement pst = conn.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS)) {

            
            pst.setInt(1, idUsuario);
            pst.setInt(2, idGrupo);

            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
