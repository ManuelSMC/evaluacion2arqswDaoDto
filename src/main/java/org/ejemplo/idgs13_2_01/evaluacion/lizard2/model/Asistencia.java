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
    
    ConnSingleton conexion = ConnSingleton.getInstance();
    
    public List<Asistencia> obtenerAsistenciasPorMateriaGrupo(int idMateria, int idGrupo, int idMaestro) {
        List<Asistencia> asistencias = new ArrayList<>();
        
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;
            
            consulta = "SELECT a.id, a.fecha, a.estado, u.nombre AS nombre_alumno "
                + "FROM Asistencias a "
                + "JOIN Alumnos al ON a.id_alumno = al.id "
                + "JOIN Usuarios u ON al.id_usuario = u.id "
                + "JOIN MaestroMateriaGrupo mmg ON a.id_asignacion = mmg.id "
                + "WHERE mmg.id_materia = ? AND mmg.id_grupo = ? AND mmg.id_maestro = ? AND al.id_grupo = mmg.id_grupo";
            
            conn = conexion.getConnection();
            
            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idMateria);
            pst.setInt(2, idGrupo);
            pst.setInt(3, idMaestro);
            rs = pst.executeQuery();

            while (rs.next()) {
                Asistencia asistencia = new Asistencia();
                asistencia.setId(rs.getInt("id"));
                asistencia.setFecha(rs.getString("fecha"));
                asistencia.setEstado(rs.getString("estado"));
                asistencia.setNombreAlumno(rs.getString("nombre_alumno"));
                asistencias.add(asistencia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return asistencias;
    }
    
    
    public boolean registrarAsistencia(int idAlumno, int idGrupo, int idMateria, int idMaestro, String fecha, String estado){
        boolean resultado = false;
        
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;
            
            
            consulta = "INSERT INTO Asistencias (id_alumno, id_asignacion, fecha, estado) " +
                          "VALUES (?, (SELECT id FROM MaestroMateriaGrupo WHERE id_materia = ? AND id_grupo = ? AND id_maestro = ? LIMIT 1), ?, ?)";
            
            conn = conexion.getConnection();
            
            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idAlumno);
            pst.setInt(2, idMateria);
            pst.setInt(3, idGrupo);
            pst.setInt(4, idMaestro);
            pst.setString(5, fecha);
            pst.setString(6, estado);

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

