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

public class HorarioDao {
   
    ConnSingleton conexion = ConnSingleton.getInstance();
    
    public List<Horario> getHorarioGrupo(int idGrupo) {
        List<Horario> horarioGrupo = new ArrayList<>();
        
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM horarios WHERE id_grupo = ?";
            
            conn = conexion.getConnection();

            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idGrupo);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                MateriaDao materiaDao = new MateriaDao();
                int idMateria = rs.getInt("id_materia");
                Materia materia = new Materia();
                materia = materiaDao.obtenerMateriaPorId(idMateria);
                
                Horario horario = new Horario(
                    rs.getInt("id"),
                    rs.getInt("id_grupo"),
                    materia,
                    rs.getString("dia"),
                    rs.getString("hora_inicio"),
                    rs.getString("hora_fin")
                );
                
                horarioGrupo.add(horario);
                 
            }
            return horarioGrupo;

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean addHorario(int idGrupo, int idMateria, String dia, String horaInicio, String horaFin) {
        try {
            Connection conn;
            PreparedStatement pst;
            String consulta;
            
            conn = conexion.getConnection();
            
            consulta = "INSERT INTO horarios (id_grupo, id_materia, dia, hora_inicio, hora_fin) VALUES (?, ?, ?, ?, ?)";
            
            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idGrupo);
            pst.setInt(2, idMateria);
            pst.setString(3, dia);
            pst.setString(4, horaInicio);
            pst.setString(5, horaFin);

            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}

