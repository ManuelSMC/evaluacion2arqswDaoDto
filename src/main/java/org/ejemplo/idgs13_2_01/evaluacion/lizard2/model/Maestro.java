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

public class Maestro {
    private int id;
    private UsuarioModel usuario;

    public Maestro() {
    }
    
    public Maestro(int id, UsuarioModel usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    ConnSingleton conexion = ConnSingleton.getInstance();
    
    public List<Maestro> getMaestros() {
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM maestros";

            UsuarioModel usuario = new UsuarioModel();
            
            conn = conexion.getConnection();
            
            pst = conn.prepareStatement(consulta);
            rs = pst.executeQuery();
            
            List<Maestro> maestros = new ArrayList<>();
            while (rs.next()) {
                Maestro maestro = new Maestro();
                maestro.setId(rs.getInt("id"));
                maestro.setUsuario(usuario.findById(rs.getInt("id_usuario")));
                maestros.add(maestro);
            }
            return maestros;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Maestro.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Maestro.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public String getMaestro(int idMaestro) {
        
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM maestros WHERE id = ?";
            
            conn = conexion.getConnection();

            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idMaestro);
            rs = pst.executeQuery();
            Maestro maestro = new Maestro();
            
            if (rs.next()) {
                maestro.setId(rs.getInt("id"));
                UsuarioModel usuario = new UsuarioModel();
                usuario = usuario.findById(rs.getInt("id_usuario"));
                String nombreMaestro = usuario.getNombre();
                return nombreMaestro;
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Grupo> obtenerGrupos(int idUsuario) {
        List<Grupo> grupos = new ArrayList<>();
        

        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT DISTINCT g.id AS id_grupo, g.nombre AS grupo, c.nombre AS carrera, c.id AS id_carrera "
                    + "FROM Maestros ma "
                    + "JOIN MaestroMateriaGrupo mmg ON ma.id = mmg.id_maestro "
                    + "JOIN Grupos g ON mmg.id_grupo = g.id "
                    + "JOIN Carreras c ON g.id_carrera = c.id "
                    + "WHERE ma.id = ?";
            
            conn = conexion.getConnection();
            
            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idUsuario);
            rs = pst.executeQuery();
            while (rs.next()) {
                Carrera carrera = new Carrera();
                carrera = carrera.getById(rs.getInt("id_carrera"));
                Grupo grupo = new Grupo(
                        rs.getInt("id_grupo"),
                        rs.getString("grupo"),
                        rs.getInt("id_carrera"),
                        carrera
                );
                grupos.add(grupo);
               
            }
            return grupos;
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    public List<Materia> obtenerMateriasGrupoMaestro(int idMaestro, int idGrupo) {
        List<Materia> materias = new ArrayList<>();
        

        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT DISTINCT m.id AS id_materia, m.nombre AS nombre_materia, g.id AS id_grupo, g.nombre AS nombre_grupo "
                    + "FROM MaestroMateriaGrupo mmg "
                    + "JOIN Materias m ON mmg.id_materia = m.id "
                    + "JOIN Grupos g ON mmg.id_grupo = g.id "
                    + "WHERE mmg.id_maestro = ? AND mmg.id_grupo = ?";
            
            conn = conexion.getConnection();
            
            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idMaestro);
            pst.setInt(2, idGrupo);
            rs = pst.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia(
                        rs.getInt("id_materia"),
                        rs.getString("nombre_materia")
                );
                materias.add(materia);
               
            }
            return materias;
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    public boolean asignarGrupoMateriaMaestro(int idMaestro, int idMateria, int idGrupo) {
        try {
            Connection conn;
            PreparedStatement pst;
            PreparedStatement verificarPst;
            String consulta;
            
            conn = conexion.getConnection();
            
            String verificarSql = "SELECT COUNT(*) FROM maestromateriagrupo WHERE id_maestro = ? AND id_materia = ? AND id_grupo = ?";
            verificarPst = conn.prepareStatement(verificarSql);
            verificarPst.setInt(1, idMaestro);
            verificarPst.setInt(2, idMateria);
            verificarPst.setInt(3, idGrupo);
            ResultSet rs = verificarPst.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return false; // ya existe ese insert entonces no se inserta uno nuevo
            }
            
            consulta = "INSERT INTO maestromateriagrupo (id_maestro, id_materia, id_grupo) VALUES (?, ?, ?)";

            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idMaestro);
            pst.setInt(2, idMateria);
            pst.setInt(3, idGrupo);

            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    public Integer getIdMaestroPorUsuario(int id_usuario) {
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT id FROM maestros WHERE id_usuario = ?";
            
            conn = conexion.getConnection();

            pst = conn.prepareStatement(consulta);

            pst.setInt(1, id_usuario);
            rs = pst.executeQuery();

            if (rs.next()) {
                int maestroId = rs.getInt("id");
                return maestroId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void addMaestro(int idUsuario) {
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "INSERT INTO maestros (id_usuario) VALUES (?)";

            conn = conexion.getConnection();

            pst = conn.prepareStatement(consulta);

            pst.setInt(1, idUsuario);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

