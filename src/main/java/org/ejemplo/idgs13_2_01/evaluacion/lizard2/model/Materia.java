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

public class Materia {
    private int id;
    private String nombre;

    public Materia() {
    }
    
    public Materia(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public String findById(int id_materia) {
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM materias WHERE id = ?";

            //driver para mysql8
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/evaluacion2?useSSL=false&allowPublicKeyRetrieval=true", "root", "Perfect97");
            
            pst = conn.prepareStatement(consulta);
            pst.setInt(1, id_materia);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                
                String nombreMateria = rs.getString("nombre");
                conn.close();
                return nombreMateria;
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

