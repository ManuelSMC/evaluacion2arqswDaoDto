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

public class Grupo {
    private int id;
    private String nombre;
    private int idCarrera;

    public Grupo() {
    }

    public Grupo(int id, String nombre, int idCarrera) {
        this.id = id;
        this.nombre = nombre;
        this.idCarrera = idCarrera;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getGrupo(int idGrupo) {
        
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM grupos WHERE id = ?";
            
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/evaluacion2?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "Perfect97");

            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idGrupo);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                String nombreGrupo = rs.getString("nombre");
                conn.close();
                return nombreGrupo;
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
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

            //driver para mysql8
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/evaluacion2?useSSL=false&allowPublicKeyRetrieval=true", "root", "Perfect97");
            
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
            conn.close();
            return listaGrupos;
            

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
}