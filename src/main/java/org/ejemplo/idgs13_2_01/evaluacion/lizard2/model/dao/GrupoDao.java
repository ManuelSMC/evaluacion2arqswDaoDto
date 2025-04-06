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

public class GrupoDao {
    
    ConnSingleton conexion = ConnSingleton.getInstance();
    
    public String getGrupo(int idGrupo) {
        
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM grupos WHERE id = ?";
            
            conn = conexion.getConnection();

            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idGrupo);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                String nombreGrupo = rs.getString("nombre");
                return nombreGrupo;
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Grupo> getGrupos() {
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM grupos";

            conn = conexion.getConnection();
            
            pst = conn.prepareStatement(consulta);
            rs = pst.executeQuery();
            
            List<Grupo> listaGrupos = new ArrayList<>();
            
            while (rs.next()) {
                Grupo grupo = new Grupo();
                grupo.setId(rs.getInt("id"));
                grupo.setNombre(rs.getString("nombre"));
                grupo.setIdCarrera(rs.getInt("id_carrera"));
                listaGrupos.add(grupo);
            }
            return listaGrupos;
            

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MateriaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(MateriaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public List<Grupo> obtenerGrupos() {
        List<Grupo> grupos = new ArrayList<>();

        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM grupos";
            
            conn = conexion.getConnection();
            
            pst = conn.prepareStatement(consulta);

            rs = pst.executeQuery();
            
            while (rs.next()) {
                CarreraDao carreraDao = new CarreraDao();
                Carrera carrera = new Carrera();
                carrera = carreraDao.getById(rs.getInt("id_carrera"));
                Grupo grupo = new Grupo(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("id_carrera"),
                        carrera
                );
                grupos.add(grupo);
               
            }
            return grupos;
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    public void addGrupo(int idCarrera, String nombreGrupo){
        
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;
            
            
            consulta = "INSERT INTO grupos (nombre, id_carrera) VALUES (?, ?)";
            
            conn = conexion.getConnection();
            
            pst = conn.prepareStatement(consulta);

            pst.setString(1, nombreGrupo);
            pst.setInt(2, idCarrera);

            pst.executeUpdate();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}