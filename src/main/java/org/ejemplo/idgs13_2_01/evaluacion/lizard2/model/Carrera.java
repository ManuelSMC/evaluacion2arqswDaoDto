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

public class Carrera {
    private int id;
    private String nombre;

    public Carrera() {
    }
    
    public Carrera(int id, String nombre) {
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

    ConnSingleton conexion = ConnSingleton.getInstance();
    
    public Carrera getById(int idCarrera) {
        
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM carreras WHERE id = ?";
            
            conn = conexion.getConnection();

            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idCarrera);
            rs = pst.executeQuery();

            if (rs.next()) {
                Carrera carrera = new Carrera(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
                return carrera;
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Carrera> getCarreras() {
        List<Carrera> carreras = new ArrayList<>();
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM carreras";

            conn = conexion.getConnection();

            pst = conn.prepareStatement(consulta);

            rs = pst.executeQuery();

            while (rs.next()) {
                Carrera carrera = new Carrera(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
                carreras.add(carrera);
                
            }
            
            return carreras;

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void addCarrera(String nombreCarrera){
        
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;
            
            
            consulta = "INSERT INTO carreras (nombre) VALUES (?)";
            
            conn = conexion.getConnection();
            
            pst = conn.prepareStatement(consulta);

            pst.setString(1, nombreCarrera);

            pst.executeUpdate();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

