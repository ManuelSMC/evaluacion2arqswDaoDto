package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Padre {
    private int id;
    private int idUsuario;

    public Padre() {
    }

    public Padre(int id, int idUsuario) {
        this.id = id;
        this.idUsuario = idUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuario() {
        return idUsuario;
    }

    public void setUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    public Integer getIdPadrePorUsuario(int id_usuario) {
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT id FROM padres WHERE id_usuario = ?";
            
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/evaluacion2?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "Perfect97");

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
        }
        return null;
    }
    
}

