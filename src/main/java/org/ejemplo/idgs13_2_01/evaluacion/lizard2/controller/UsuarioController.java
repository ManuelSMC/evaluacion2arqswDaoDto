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
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.UsuarioModel;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao.UsuarioModelDao;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.Alumno;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao.AlumnoDao;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.Calificacion;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao.CalificacionDao;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.Asistencia;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao.AsistenciaDao;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.Materia;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao.MateriaDao;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dto.Padre;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.dao.PadreDao;


@WebServlet(name = "UsuarioController", urlPatterns = {"/login", "/materiasAlumno", "/asistencias", "/cerrar-sesion", "/asistenciasAlumno", "/homepage"})
public class UsuarioController extends HttpServlet {
    
    UsuarioModel usuarioModel = new UsuarioModel();
    UsuarioModelDao usuarioModelDao = new UsuarioModelDao();
    List<Asistencia> asistencias;
    Padre padre = new Padre();
    PadreDao padreDao = new PadreDao();
    String ruta = "";
    int idMateria;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");
        
        Alumno alumno = new Alumno();
        AlumnoDao alumnoDao = new AlumnoDao();
        
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
                System.out.println("Rol del usuario: " + usuario.getRol());
                if (usuario.getRol().equals("Padre")) {
                    System.out.println("idUsuarioPadre" + usuario.getId());
                    Integer idPadre = padreDao.getIdPadrePorUsuario(usuario.getId());
                    
                    alumno = alumnoDao.getByIdPadre(idPadre);
                    usuario = usuarioModelDao.findById(alumno.getIdUsuario());
                    request.setAttribute("usuario", usuario);
                    alumno = alumnoDao.getById(usuario.getId());
                    List<Calificacion> calificaciones = alumnoDao.obtenerCalificaciones(usuario.getId());
                    request.setAttribute("calificaciones", calificaciones);
                    if (alumno != null) {
                        /*for (Calificacion calificacion : calificaciones) {
                        System.out.println("Materia: " + calificacion.getMateria().getNombre() + ", Calificación: " + calificacion.getCalificacion());
                    }*/
                    } else {
                        request.setAttribute("error", "No se encontraron datos del alumno.");
                    }
                }else  if (usuario.getRol().equals("Servicios Escolares")) {
                    String intIdUsuario = request.getParameter("idUsuario");
                    int idUsuario = Integer.parseInt(intIdUsuario);
                    request.setAttribute("idUsuario", idUsuario);
                    alumno = alumnoDao.getById(idUsuario);
                    List<Calificacion> calificaciones = alumnoDao.obtenerCalificaciones(idUsuario);
                    request.setAttribute("calificaciones", calificaciones);
                    if (alumno != null) {
                        /*for (Calificacion calificacion : calificaciones) {
                        System.out.println("Materia: " + calificacion.getMateria().getNombre() + ", Calificación: " + calificacion.getCalificacion());
                    }*/
                    } else {
                        request.setAttribute("error", "No se encontraron datos del alumno.");
                    }
                }else{
                    alumno = alumnoDao.getById(usuario.getId());
                    System.out.println("IdUsuario:: " + usuario.getId());
                    List<Calificacion> calificaciones = alumnoDao.obtenerCalificaciones(usuario.getId());
                    request.setAttribute("calificaciones", calificaciones);
                    if (alumno != null) {
                        /*for (Calificacion calificacion : calificaciones) {
                        System.out.println("Materia: " + calificacion.getMateria().getNombre() + ", Calificación: " + calificacion.getCalificacion());
                    }*/
                    } else {
                        request.setAttribute("error", "No se encontraron datos del alumno.");
                    }
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
                    Integer idPadre = padreDao.getIdPadrePorUsuario(usuario.getId());
                    
                    alumno = alumnoDao.getByIdPadre(idPadre);
                    usuario = usuarioModelDao.findById(alumno.getIdUsuario());
                    String idMateriaStr = request.getParameter("idMateria");
                    idMateria = Integer.parseInt(idMateriaStr);
                
                    alumno = alumnoDao.getById(usuario.getId());
                }else  if (usuario.getRol().equals("Servicios Escolares")) {
                    String intIdUsuarioReal = request.getParameter("idUsuario");
                    int idUsuario = Integer.parseInt(intIdUsuarioReal);
                    request.setAttribute("idUsuario", idUsuario);
                    
                    String idMateriaStr = request.getParameter("idMateria");
                    idMateria = Integer.parseInt(idMateriaStr);
                
                    alumno = alumnoDao.getById(idUsuario);
                }else{
                    String idMateriaStr = request.getParameter("idMateria");
                    idMateria = Integer.parseInt(idMateriaStr);
                
                    alumno = alumnoDao.getById(usuario.getId());
                }
                
                
                
                /*System.out.println("AlumnoId: " + alumno.getId());
                System.out.println("AlumnoIdGrupo: " + alumno.getIdGrupo());
                System.out.println("AlumnoIdPadre: " + alumno.getIdPadre());
                System.out.println("AlumnoIdUsuario: " + alumno.getIdUsuario());*/
                
                if (alumno != null) {
                    
                    asistencias = alumnoDao.obtenerAsistencias(alumno.getId(), idMateria);
                    Materia materia = new Materia();
                    MateriaDao materiaDao = new MateriaDao();
                    System.out.println("idMateria: " + idMateria);
                    String nombre_materia = materiaDao.findById(idMateria);
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

            UsuarioModel usuario = usuarioModelDao.loginUsuario(correo, contrasena);
            
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
