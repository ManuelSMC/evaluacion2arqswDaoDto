package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao;

import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MaestroMateriaGrupoDao {
    
    ConnSingleton conexion = ConnSingleton.getInstance();
    
    public MaestroMateriaGrupo getById(int id_asignacion){
        
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM maestromateriagrupo WHERE id = ?";

            conn = conexion.getConnection();
            
            pst = conn.prepareStatement(consulta);
            pst.setInt(1, id_asignacion);
            rs = pst.executeQuery();

            if (rs.next()) {
                MaestroMateriaGrupo maestroMateriaGrupo = new MaestroMateriaGrupo();
                maestroMateriaGrupo.setId(rs.getInt("id"));
                maestroMateriaGrupo.setIdMaestro(rs.getInt("id_maestro"));
                maestroMateriaGrupo.setIdMateria(rs.getInt("id_materia"));
                maestroMateriaGrupo.setIdGrupo(rs.getInt("id_grupo"));
                return maestroMateriaGrupo;
            }
            

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MateriaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(MateriaDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    
}
