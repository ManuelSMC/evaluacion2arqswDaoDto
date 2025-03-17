package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    
    public MaestroMateriaGrupo getById(int id_asignacion){
        
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM maestromateriagrupo WHERE id = ?";

            //driver para mysql8
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/evaluacion2?useSSL=false&allowPublicKeyRetrieval=true", "root", "Perfect97");
            
            pst = conn.prepareStatement(consulta);
            pst.setInt(1, id_asignacion);
            rs = pst.executeQuery();

            if (rs.next()) {
                MaestroMateriaGrupo maestroMateriaGrupo = new MaestroMateriaGrupo();
                maestroMateriaGrupo.setId(rs.getInt("id"));
                maestroMateriaGrupo.setIdMaestro(rs.getInt("id_maestro"));
                maestroMateriaGrupo.setIdMateria(rs.getInt("id_materia"));
                maestroMateriaGrupo.setIdGrupo(rs.getInt("id_grupo"));
                conn.close();
                return maestroMateriaGrupo;
            }
            

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    
}
