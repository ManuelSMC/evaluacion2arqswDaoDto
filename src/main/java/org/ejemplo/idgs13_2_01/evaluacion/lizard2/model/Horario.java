package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Horario {
    private int id;
    private int idGrupo;
    private Materia materia;
    private String dia;
    private String horaInicio;
    private String horaFin;

    public Horario() {
    }

    public Horario(int id, int idGrupo, Materia materia, String dia, String horaInicio, String horaFin) {
        this.id = id;
        this.idGrupo = idGrupo;
        this.materia = materia;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrupo() {
        return idGrupo;
    }

    public void setGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public List<Horario> getHorarioGrupo(int idGrupo) {
        List<Horario> horarioGrupo = new ArrayList<>();
        Materia materia = new Materia();
        
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM horarios WHERE id_grupo = ?";
            
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/evaluacion2?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "Perfect97");

            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idGrupo);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                int idMateria = rs.getInt("id_materia");
                
                materia = materia.obtenerMateriaPorId(idMateria);
                
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
            conn.close();  
            return horarioGrupo;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean addHorario(int idGrupo, int idMateria, String dia, String horaInicio, String horaFin) {
        try {
            Connection conn;
            PreparedStatement pst;
            String consulta;
            
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/evaluacion2?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "Perfect97");
            
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
            return false;
        }
        
    }
    
}

