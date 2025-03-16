package org.ejemplo.idgs13_2_01.evaluacion.lizard2.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.UsuarioModel;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Alumno;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Calificacion;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Asistencia;
import org.ejemplo.idgs13_2_01.evaluacion.lizard2.model.Materia;

@WebServlet(name = "UsuarioController", urlPatterns = {"/login", "/materiasAlumno", "/asistencias", "/cerrar-sesion", "/asistenciasAlumno"})
public class UsuarioController extends HttpServlet {
    UsuarioModel usuarioModel = new UsuarioModel();
    String ruta = "";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuario");

        if (usuario == null || (!usuario.getRol().equals("Alumno") && !usuario.getRol().equals("Padre"))) {
            response.sendRedirect("login");
            return;
        }

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
                
                alumno = alumno.obtenerAlumnoPorUsuario(usuario.getId());
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
                String idMateriaStr = request.getParameter("idMateria");
                int idMateria = Integer.parseInt(idMateriaStr);
                
                alumno = alumno.obtenerAlumnoPorUsuario(usuario.getId());
                /*System.out.println("AlumnoId: " + alumno.getId());
                System.out.println("AlumnoIdGrupo: " + alumno.getIdGrupo());
                System.out.println("AlumnoIdPadre: " + alumno.getIdPadre());
                System.out.println("AlumnoIdUsuario: " + alumno.getIdUsuario());*/
                
                if (alumno != null) {
                    
                    List<Asistencia> asistencias = alumno.obtenerAsistencias(usuario.getId(), idMateria);
                    Materia materia = new Materia();
                    String nombre_materia = materia.findById(idMateria);
                    request.setAttribute("asistencias", asistencias);
                    request.setAttribute("nombre_materia", nombre_materia);
                    
                    /*for (Asistencia asistencia: asistencias) {
                        System.out.println("Fecha: " + asistencia.getFecha() + ", Asistencia: " + asistencia.getEstado());
                    }*/
                } else {
                    request.setAttribute("error", "No se encontraron datos del alumno.");
                }
                request.getRequestDispatcher("/WEB-INF/alumnos/asistenciasAlumno.jsp").forward(request, response);
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

            UsuarioModel usuario = usuarioModel.loginUsuario(correo, contrasena);

            if (usuario != null) {
                request.getSession().setAttribute("usuario", usuario);
                if (usuario.getRol().equals("Alumno") || usuario.getRol().equals("Padre")) {
                    response.sendRedirect("materiasAlumno");
                } else {
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
