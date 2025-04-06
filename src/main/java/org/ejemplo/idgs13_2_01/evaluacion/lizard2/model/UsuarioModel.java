package org.ejemplo.idgs13_2_01.evaluacion.lizard2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioModel {
    private int id;
    private String nombre;
    private String correo;
    private String contrasena;
    private String rol;

    public UsuarioModel() {
    }

    public UsuarioModel(int id, String nombre, String correo, String contrasena, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    ConnSingleton conexion = ConnSingleton.getInstance();

    public UsuarioModel loginUsuario(String correo, String contrasena){
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?";
            conn = conexion.getConnection();
            
            pst = conn.prepareStatement(consulta);
            pst.setString(1, correo);
            pst.setString(2, contrasena);
            
            rs = pst.executeQuery();
            if (rs.next()) {
                UsuarioModel usuario = new UsuarioModel();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
                return usuario;
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    
    public UsuarioModel findById(int id_usuario) {
        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM usuarios WHERE id = ?";

            conn = conexion.getConnection();
            
            pst = conn.prepareStatement(consulta);
            pst.setInt(1, id_usuario);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                UsuarioModel usuario = new UsuarioModel();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
                return usuario;
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
    
    public UsuarioModel addUsuario(String nombre, String correo, String contrasena, String rol) {
        UsuarioModel usuario = null;
        String consulta = "INSERT INTO Usuarios (nombre, correo, contrasena, rol) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexion.getConnection();
             PreparedStatement pst = conn.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS)) {
            
            pst.setString(1, nombre);
            pst.setString(2, correo);
            pst.setString(3, contrasena);
            pst.setString(4, rol);

            int filasAfectadas = pst.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet rs = pst.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGenerado = rs.getInt(1);
                        usuario = new UsuarioModel(idGenerado, nombre, correo, contrasena, rol);
                    }
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public List<UsuarioModel> getUsuarios(String rolUsuario){
        List<UsuarioModel> usuarios = new ArrayList<>();
        

        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT * FROM usuarios WHERE rol = ?";
            
            conn = conexion.getConnection();
            
            pst = conn.prepareStatement(consulta);

            pst.setString(1, rolUsuario);
            
            rs = pst.executeQuery();
            while (rs.next()) {
                UsuarioModel usuario = new UsuarioModel();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
        
    }
    
    public List<UsuarioModel> getUsuariosAlumnos(String rolUsuario){
        List<UsuarioModel> usuarios = new ArrayList<>();
        

        try {
            Connection conn;
            ResultSet rs;
            PreparedStatement pst;
            String consulta;

            consulta = "SELECT u.id, u.nombre, u.correo, u.contrasena, u.rol FROM Usuarios u " +
                   "JOIN Alumnos a ON u.id = a.id_usuario " +
                   "WHERE u.rol = ? AND a.id_padre IS NULL";
            
            conn = conexion.getConnection();
            
            pst = conn.prepareStatement(consulta);

            pst.setString(1, rolUsuario);
            
            rs = pst.executeQuery();
            while (rs.next()) {
                UsuarioModel usuario = new UsuarioModel();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Materia.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
        
    }
    
}
