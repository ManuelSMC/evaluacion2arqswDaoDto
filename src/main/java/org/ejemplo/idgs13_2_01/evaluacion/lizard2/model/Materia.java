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

    public Materia obtenerMateriaPorId(int id_materia) {
        Materia materia = null;
        
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
                materia = new Materia(
                    rs.getInt("id"),
                    rs.getString("nombre")
                );
            }
            conn.close();
            return materia;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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
    
    public List<Materia> getMaterias() {
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM materias";

            //driver para mysql8
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/evaluacion2?useSSL=false&allowPublicKeyRetrieval=true", "root", "Perfect97");
            
            pst = conn.prepareStatement(consulta);
            rs = pst.executeQuery();
            
            List<Materia> listaMaterias = new ArrayList<>();
            
            while (rs.next()) {
                Materia mat = new Materia();
                mat.setId(rs.getInt("id"));
                mat.setNombre(rs.getString("nombre"));
                listaMaterias.add(mat);
            }
            conn.close();
            return listaMaterias;
            

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public boolean addMateria(String nombreMateria) {
        try {
            Connection conn;
            PreparedStatement pst;
            String consulta;
            
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/evaluacion2?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "Perfect97");
            
            consulta = "INSERT INTO materias (nombre) VALUES (?)";



            pst = conn.prepareStatement(consulta);

            pst.setString(1, nombreMateria);

            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }
}

