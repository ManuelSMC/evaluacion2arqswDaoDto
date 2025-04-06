package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao;

import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PadreDao {
    
    ConnSingleton conexion = ConnSingleton.getInstance();
    
    public Integer getIdPadrePorUsuario(int id_usuario) {
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT id FROM padres WHERE id_usuario = ?";
            
            conn = conexion.getConnection();

            pst = conn.prepareStatement(consulta);

            pst.setInt(1, id_usuario);
            rs = pst.executeQuery();

            if (rs.next()) {
                int padreId = rs.getInt("id");
                return padreId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }    catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Padre addPadre(int idUsuario) {
        Padre padre = null;
        String consulta = "INSERT INTO padres (id_usuario) VALUES (?)";

        try (Connection conn = conexion.getConnection();
             PreparedStatement pst = conn.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS)) {

            pst.setInt(1, idUsuario);

            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGenerado = rs.getInt(1);
                        padre = new Padre(idGenerado, idUsuario);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return padre;
    }
    
    public void addPadreAlumno(int idPadre, int idAlumnoHijo){
        
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;
            
            
            consulta = "UPDATE alumnos SET id_padre = ? WHERE id = ?";
            
            conn = conexion.getConnection();
            
            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idPadre);
            pst.setInt(2, idAlumnoHijo);

            pst.executeUpdate();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

