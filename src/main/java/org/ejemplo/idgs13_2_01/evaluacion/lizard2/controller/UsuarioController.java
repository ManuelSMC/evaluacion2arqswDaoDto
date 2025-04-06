package org.ejemplo.idgs13_2_01.evaluacion.lizard2.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.UsuarioModel;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Alumno;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Calificacion;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Asistencia;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.ConnSingleton;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Materia;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Padre;

@WebServlet(name = "UsuarioController", urlPatterns = {"/login", "/materiasAlumno", "/asistencias", "/cerrar-sesion", "/asistenciasAlumno", "/homepage"})
public class UsuarioController extends HttpServlet {
    
    ConnSingleton conexion = ConnSingleton.getInstance();
    
    UsuarioModel usuarioModel = new UsuarioModel();
    List<Asistencia> asistencias;
    Padre padre = new Padre();
    String ruta = "";
    int idMateria;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");

        Alumno alumno = new Alumno();
        
        ruta = request.getServletPath();
        
        switch (ruta) {
            case "/login":
                String error = request.getParameter("error");
                request.setAttribute("error", error);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
                
            case "/cerrar-sesion":
                request.getSession().invalidate();

                response.sendRedirect("login");
                break;

                
            case "/materiasAlumno":
                if (usuario == null) {
                    response.sendRedirect("login");
                    return;
                }
                if (usuario.getRol().equals("Padre")) {
                    System.out.println("idUsuarioPadre" + usuario.getId());
                    Integer idPadre = padre.getIdPadrePorUsuario(usuario.getId());
                    
                    alumno = alumno.getByIdPadre(idPadre);
                    usuario = usuario.findById(alumno.getIdUsuario());
                    request.setAttribute("usuario", usuario);
                    alumno = alumno.getById(usuario.getId());
                }else  if (usuario.getRol().equals("Servicios Escolares")) {
                    String intIdUsuario = request.getParameter("idUsuario");
                    int idUsuario = Integer.parseInt(intIdUsuario);
                    request.setAttribute("idUsuario", idUsuario);
                    alumno = alumno.getById(idUsuario);
                }else{
                    alumno = alumno.getById(usuario.getId());
                }
                
                
                /*System.out.println("AlumnoId: " + alumno.getId());
                System.out.println("AlumnoIdGrupo: " + alumno.getIdGrupo());
                System.out.println("AlumnoIdPadre: " + alumno.getIdPadre());
                System.out.println("AlumnoIdUsuario: " + alumno.getIdUsuario());*/
                
                if (alumno != null) {
                    
                    List<Calificacion> calificaciones = alumno.obtenerCalificaciones(usuario.getId());
                    request.setAttribute("calificaciones", calificaciones);
                    
                    /*for (Calificacion calificacion : calificaciones) {
                        System.out.println("Materia: " + calificacion.getMateria().getNombre() + ", Calificación: " + calificacion.getCalificacion());
                    }*/
                } else {
                    request.setAttribute("error", "No se encontraron datos del alumno.");
                }
                request.getRequestDispatcher("/WEB-INF/alumnos/materiasAlumno.jsp").forward(request, response);
                break;
                
            case "/asistenciasAlumno":
                if (usuario == null) {
                    response.sendRedirect("login");
                    return;
                }
                
                if (usuario.getRol().equals("Padre")) {
                    System.out.println("idUsuarioPadre" + usuario.getId());
                    Integer idPadre = padre.getIdPadrePorUsuario(usuario.getId());
                    
                    alumno = alumno.getByIdPadre(idPadre);
                    usuario = usuario.findById(alumno.getIdUsuario());
                    String idMateriaStr = request.getParameter("idMateria");
                    idMateria = Integer.parseInt(idMateriaStr);
                
                    alumno = alumno.getById(usuario.getId());
                }else  if (usuario.getRol().equals("Servicios Escolares")) {
                    String intIdUsuarioReal = request.getParameter("idUsuario");
                    int idUsuario = Integer.parseInt(intIdUsuarioReal);
                    request.setAttribute("idUsuario", idUsuario);
                    
                    String idMateriaStr = request.getParameter("idMateria");
                    idMateria = Integer.parseInt(idMateriaStr);
                
                    alumno = alumno.getById(idUsuario);
                }else{
                    String idMateriaStr = request.getParameter("idMateria");
                    idMateria = Integer.parseInt(idMateriaStr);
                
                    alumno = alumno.getById(usuario.getId());
                }
                
                
                
                /*System.out.println("AlumnoId: " + alumno.getId());
                System.out.println("AlumnoIdGrupo: " + alumno.getIdGrupo());
                System.out.println("AlumnoIdPadre: " + alumno.getIdPadre());
                System.out.println("AlumnoIdUsuario: " + alumno.getIdUsuario());*/
                
                if (alumno != null) {
                    
                    asistencias = alumno.obtenerAsistencias(alumno.getId(), idMateria);
                    Materia materia = new Materia();
                    System.out.println("idMateria: " + idMateria);
                    String nombre_materia = materia.findById(idMateria);
                    request.setAttribute("asistencias", asistencias);
                    request.setAttribute("nombre_materia", nombre_materia);
                    System.out.println("nombre_materia: " + nombre_materia);
                    
                } else {
                    request.setAttribute("error", "No se encontraron datos del alumno.");
                }
                request.getRequestDispatcher("/WEB-INF/alumnos/asistenciasAlumno.jsp").forward(request, response);
                break;
            case "/homepage":
                if (usuario == null) {
                    response.sendRedirect("login");
                    return;
                }
                request.getRequestDispatcher("/WEB-INF/homepage.jsp").forward(request, response);
                break;
                
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ruta = request.getServletPath();

        if ("/login".equals(ruta)) {
            String correo = request.getParameter("username");
            String contrasena = request.getParameter("password");

            // mostrar la conexión para probar que singleton funciona
            UsuarioModel usuario = usuarioModel.loginUsuario(correo, contrasena);
            
            System.out.println("Conexion bd login: " + conexion.getConn());
            if (usuario != null) {
                request.getSession().setAttribute("usuario", usuario);
                if (usuario.getRol().equals("Alumno") || usuario.getRol().equals("Padre")) {
                    response.sendRedirect("materiasAlumno");
                } else if(usuario.getRol().equals("Coordinador")){
                    response.sendRedirect("homepage");
                }else if(usuario.getRol().equals("Maestro")){
                    response.sendRedirect("gruposMaestro");
                }else if(usuario.getRol().equals("Director")){
                    response.sendRedirect("grupos");
                }else if(usuario.getRol().equals("Recursos Humanos")){
                    response.sendRedirect("homepage");
                }else if(usuario.getRol().equals("SuperUsuario")){
                    response.sendRedirect("homepage");
                }else if(usuario.getRol().equals("Servicios Escolares")){
                    response.sendRedirect("homepage");
                }
            } else {
                response.sendRedirect("login?error=true");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Controlador para el manejo de usuarios y materias.";
    }
}
